package com.example.crack.basic_1_noxml

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*=======================================*/
        // TO-Do: 여기서부터 우리가 코딩!
        /*=======================================*/

        // 코드로 화면을 그리는 노가다 예제
        // 레아아웃을 생성 및 오리엔테이션 및 색상지정

        // LinearLayout형 변수를 선언
        // 배경을 다크그레이로 설정
        // 방향을 버티컬(세로)로 지정
        val ln: LinearLayout
        ln = LinearLayout(this)
        ln.setBackgroundColor(Color.DKGRAY)
        ln.orientation = LinearLayout.VERTICAL

        // TextView와 Button을 생성
        val txtTest = TextView(this)
        txtTest.text = "코딩으로 만든 텍스트 "
        txtTest.setTextColor(Color.WHITE)

        val btn = Button(this)
        btn.text = "코딩으로 만든 버튼 "

        // 텍스트를 붙인다.
        ln.addView(txtTest)

        // 버튼을 붙인다.
        ln.addView(btn)

        // 만든 화면을 Activity에 붙인다.
        setContentView(ln)
        //setContentView(R.layout.activity_main);
    }
}
