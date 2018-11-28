package com.example.crack.basic_4_dialog

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAlert.setOnClickListener {
            simpleAlertDialog()
        }

        btnCustom.setOnClickListener {
            val dlg = MyDialog(this)
            // 종료되었을 경우,
            dlg.setOnDismissListener {
                Toast.makeText(this, "${dlg.dayString}입니다.",
                               Toast.LENGTH_LONG).show()
            }
            dlg.show()
        }

    }

    private fun simpleAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("타이틀입니다")

        val input = EditText(this)

        // 동적으로 Control을 만들고 붙일 수 있다.
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setMessage("메시지입니다")

        builder.setPositiveButton("OK", {
                dialog, which ->
                title = input.text.toString() })

        builder.setNegativeButton("Cancel", { dialog, which -> dialog.cancel()} )
        builder.show()
    }
}
