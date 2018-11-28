package com.example.crack.using_fragment_1

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    // Fragment 배열처리
    var lstFragment : List<Fragment>? = listOf (FragmentOne(), FragmentTwo())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUiUI()
    }

    private fun setUiUI() {
        setUpSpinner()

        // Fragment를 Framelayout(R.id.frContainer)에 적용한다.
        fragmentManager
            .beginTransaction()
            .apply { replace (R.id.frContainer, lstFragment!!.get(0) ); commit() }
    }

    // Spinner 화면처리
    private fun setUpSpinner() {
        val list = ArrayList<String>()
        list.add("Fragment One")
        list.add("Fragment Two")
        val dataAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, list
        )

        spnFragment.adapter = dataAdapter
        spnFragment.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            // Item이 선택되었을 때
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {

                fragmentManager
                    .beginTransaction()
                    .apply { replace (R.id.frContainer, lstFragment!!.get(position) ); commit() }
            }

        }
    }
}
