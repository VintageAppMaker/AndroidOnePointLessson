package com.example.crack.basic_3_activitytheme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContentView 이전에 실행해야 한다.
        setTheme(R.style.MyTheme)
        setHideTitle()
        //setFullScreen()

        setContentView(R.layout.activity_main)

    }

    private fun setFullScreen() {
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    private fun setHideTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar()!!.hide();
    }
}
