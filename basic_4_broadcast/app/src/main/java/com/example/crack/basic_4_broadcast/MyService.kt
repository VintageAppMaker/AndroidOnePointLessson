package com.example.crack.basic_4_broadcast

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter



class MyService : Service() {

    companion object {
        val MY_INTENT = "com.psw.android.test.intent"
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            startDynamicBroadCastReceiver()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    /// 늦게 초기화한다.
    val mReceiver : BroadcastReceiver by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == MY_INTENT) {
                    showDialog(context)
                }
            }
        }
    }

    private fun showDialog(context : Context ) {
        val i = Intent(context, MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.putExtra("message", "user broadcast")
        context.startActivity(i)
    }


    private fun startDynamicBroadCastReceiver() {
        val theFilter = IntentFilter()
        theFilter.addAction(MY_INTENT)
        registerReceiver(this.mReceiver, theFilter)
    }

}
