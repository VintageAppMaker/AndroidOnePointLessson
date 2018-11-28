package com.example.crack.basic_1_usingxml

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // [TODO] 여기서부터 코딩
        var txt = findViewById<TextView>(R.id.txtHello)
        txt.text = "안녕하세요"
        txt.textSize = 32.0F
        txt.setTextColor(Color.parseColor("#FF0000"))

    }
}
