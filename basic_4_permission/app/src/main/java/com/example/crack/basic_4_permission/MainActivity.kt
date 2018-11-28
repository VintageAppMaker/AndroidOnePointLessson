package com.example.crack.basic_4_permission

// 퍼미션 상수를 import
import android.Manifest.permission.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.os.Build
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val b: Boolean = checkPermission()
            if (b == false) {
                requestPermission()
            } else {
                setUpUIComplte()
            }

        } else {
            setUpUIComplte()
        }

    }

    // ******이 부분만 수정하면 됨 *********
    // 퍼미션 종류를 나열함.
    var PERMISSIONS = arrayOf(READ_PHONE_STATE, ACCESS_COARSE_LOCATION)

    private fun checkPermission(): Boolean {
        for (i in PERMISSIONS.indices) {
            val result = ActivityCompat.checkSelfPermission(applicationContext, PERMISSIONS[i])
            if (result != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }

    private val REQUEST_CODE = 10001
    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> if (grantResults.size > 0) {

                var bResult = true
                grantResults.forEach {
                    if ( it != PackageManager.PERMISSION_GRANTED){
                    bResult = false
                    return@forEach
                }}

                if ( bResult){
                    Toast.makeText(this, "모든 기능을 사용가능합니다.", Toast.LENGTH_LONG).show()
                    setUpUIComplte()
                }  else{
                    Toast.makeText(this, "필수기능을 거부하셨습니다. 사용상 제약이 있습니다.", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    private fun setUpUIComplte() {
        txtMessage.text = "퍼미션이 완료됨"
    }
}
