package com.example.crack.basic_4_service

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { LogCountInfo() }
        return super.onStartCommand(intent, flags, startId)
    }

    // 3초간격으로 handleMessage()를 호출한다.
    internal var handler: Handler? = null
    var nCount = 0
    private fun LogCountInfo() {
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                Log.d("ALLTEST", String.format("service count-->%d", nCount++))
                if (nCount == 10) {

                    val url = "https://vintageappmaker.tumblr.com/"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

                }

                if (nCount < 100) {
                    handler!!.sendEmptyMessageDelayed(0, 3000)
                }

            }
        }

        handler!!.sendEmptyMessage(0)
    }
}
