package com.example.crack.basic_1_textview

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var nCount : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*=======================================*/
        // TO-Do: 여기서부터 우리가 코딩!
        /*=======================================*/
        txtNormal.setOnClickListener {
            txtNormal.apply {
                setBackgroundColor(Color.RED)
                text = "Clicked!! ${nCount++}"
                setTextColor(Color.WHITE)
                setTextSize(28.0F)
            }
        }

        txtHTML.setOnClickListener {
            // setOnClickListener는 파라메터로 클릭한 Control인 View 객체를 넘겨준다.
            // 이름을 따로 정의하지 않으면, it으로 되어 있다.
            // it을 TextView로 캐스팅하고 사용할 수 있다.
            val htmlText = it as TextView
            htmlText.text = Html.fromHtml("<h1>Hi</h1>HTML<p style=\"color:red;\">Red</idv>")

        }

    }
}
