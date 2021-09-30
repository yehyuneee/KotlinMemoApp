package com.example.kotlinmemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinmemoapp.databinding.MemoItemLayoutBinding

class MemoActivity : AppCompatActivity() {

    private lateinit var memoBiding: MemoItemLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        memoBiding = DataBindingUtil.setContentView(this, R.layout.activity_memo)
    }
}