package com.example.kotlinmemoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
//                // 기본 사용법
//                val intent = Intent(this@IntroActivity, MainActivity::class.java)
//                startActivity(intent)

                // inline class로 표현
                startMainActivity<MainActivity>()
            }
        }, 1000)
    }
}

inline fun <reified T : Activity> Context.startMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
}