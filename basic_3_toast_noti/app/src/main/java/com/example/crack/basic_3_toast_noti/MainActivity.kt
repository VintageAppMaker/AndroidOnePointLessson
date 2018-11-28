package com.example.crack.basic_3_toast_noti

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToast.setOnClickListener {
            Toast.makeText(application, "안녕하세요", Toast.LENGTH_LONG).show()
        }

        btnToast2.setOnClickListener {
            showCustom("안녕하세요!")
        }

        btnNoti.setOnClickListener {
            NewMessageNotification.notify(application, "Hi", 3)
        }
    }

    private fun showCustom( s : String) {
        val layoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = layoutInflater.inflate(R.layout.custom_toast, null)

        // layout 안의 txtMessage
        layout.txtMessage.text = s

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}
