package com.example.kotlinmemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinmemoapp.data.MemoDB
import com.example.kotlinmemoapp.data.MemoData
import com.example.kotlinmemoapp.databinding.ActivityMainBindingImpl

class MainActivity : AppCompatActivity() {

    private lateinit var mainBiding: ActivityMainBindingImpl
    private var memoDB: MemoDB? = null
    private var memoList: List<MemoData> = listOf<MemoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBiding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        memoDB = MemoDB.getInstance(this)

        // 메인쓰레드에서 Room DB에 접근하려고 하면 에러 발생한다.
        // **** 메인 UI 화면이 오랫동안 멈춰있을 수도 있기 때문에 , 메인 쓰레드에서는 데이터베이스에 접근할 수 없다.
        // 백그라운드에서 작업해야한다 (Thread, AsyncTask..)
        val r =  Runnable {
            memoList = memoDB?.memoDao()?.getAllMemo()!!
            // !! : 강제로 null이 아님을 명시해줌
        }
        val thread = Thread(r)
        thread.start()
    }
}