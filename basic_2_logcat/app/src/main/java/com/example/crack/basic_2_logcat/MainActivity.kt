package com.example.crack.basic_2_logcat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var nCount    : Int = 0
    val nMaxCount : Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogcatTest.setOnClickListener {

            Log.d(javaClass.name, "${nCount++} clicked")

            try{
                val nResult = nMaxCount / (nMaxCount - nCount)
                Log.d("MyLog", "nMaxCount / (nMaxCount - nCount) is ${nResult} ")

            } catch ( e : Exception ){
                Log.d("MyLog", "${nCount} : ${e.toString()}")

            }


        }
    }
}
