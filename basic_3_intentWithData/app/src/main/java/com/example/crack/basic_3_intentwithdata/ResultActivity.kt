package com.example.crack.basic_3_intentwithdata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }

    // 화면이 핸드폰에서 보일 떄 호출되는 메소드(함수)
    override fun onStart() {
        super.onStart()

        // 값이 없으면 리턴
        val i = intent ?: return

        val sID     = i.getStringExtra(MainActivity.ID)
        val sPasswd = i.getStringExtra(MainActivity.PASSWD)

        txtMessage.text = "아이디: ${sID}\n패스워드: ${sPasswd}"
        i.putExtra(MainActivity.RESULT, txtMessage.text.toString())

        setResult(MainActivity.REQUEST, i)
    }
}
