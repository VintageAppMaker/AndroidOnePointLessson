package com.example.crack.basic_4_broadcast

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*

// Activity에서 상속받는다.
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        txtMessage.text = ""

        // 동적 BroadcastReceiver 테스트 위해 Service 실행
        startService(Intent(this, MyService::class.java))
    }

    override fun onStart() {
        super.onStart()
        intent?.let{
            txtMessage.text = it.getStringExtra("message")
        }
    }
}
