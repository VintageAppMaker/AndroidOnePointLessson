package com.example.crack.basic_1_button

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

// Control 정보를 가져온다. kotlinx.android.synthetic.main 다음에 해당 Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*=======================================*/
        // TO-Do: 여기서부터 우리가 코딩!
        /*=======================================*/

        // ID값으로 Control 변수를 사용. java style
        btn1.setOnClickListener(
            View.OnClickListener(){
                btn1.setText("Click~~!!")
                btn1.setTextColor(Color.parseColor("#333333"))
                btn1.setBackgroundColor(Color.parseColor("#FFFF33"))
        })

        // ID값으로 Control 변수를 사용. kotlin style
        btn2.setOnClickListener {
            btn2.apply{
                text = "Click~~!!"
                setTextColor(Color.parseColor("#333333"))
                setBackgroundColor(Color.parseColor("#FFFF33"))
            }
        }

    }
}
