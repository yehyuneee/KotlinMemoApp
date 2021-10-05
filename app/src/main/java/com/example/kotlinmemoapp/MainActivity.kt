package com.example.kotlinmemoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinmemoapp.adapter.MemoAdapter
import com.example.kotlinmemoapp.data.MemoDB
import com.example.kotlinmemoapp.data.MemoData
import com.example.kotlinmemoapp.data.MemoRespository
import com.example.kotlinmemoapp.data.MemoViewModel
import com.example.kotlinmemoapp.databinding.ActivityMainBindingImpl
import androidx.lifecycle.ViewModel as ViewModels

class MainActivity : Activity(), View.OnClickListener {

    private lateinit var mainBiding: ActivityMainBindingImpl
    private var memoDB: MemoDB? = null
//    private lateinit var memoViewModel: MemoViewModel by ViewModels
    private lateinit var memoList: LiveData<List<MemoData>>

    private lateinit var memoAdapter: MemoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBiding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBiding.main = this

        memoDB = MemoDB.getInstance(this)

        // 메인쓰레드에서 Room DB에 접근하려고 하면 에러 발생한다.
        // **** 메인 UI 화면이 오랫동안 멈춰있을 수도 있기 때문에 , 메인 쓰레드에서는 데이터베이스에 접근할 수 없다.
        // 백그라운드에서 작업해야한다 (Thread, AsyncTask..)
        // ### 코루틴으로 백그라운드에서 작업하는 구조로 바꿔야할 것 같음....! 공부필요
        val r = Runnable {
            memoList = memoDB?.memoDao()?.getAllMemo()!!
            // !! : 강제로 null이 아님을 명시해줌
        }
        val thread = Thread(r)
        thread.start()

        // 마저 참고 ---> https://todaycode.tistory.com/34
    }

    fun setInit() {
        memoAdapter = MemoAdapter(context = this)
        memoAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.add_memo_btn) {
            // 메모 등록 버튼 --> 메모 등록 액티비티 이동
            moveMemoActivity<MemoActivity>()
        }
    }

    /**
     * 메모 등록 Activity 호출
     */
    inline fun <reified T : Activity> Context.moveMemoActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}