package com.example.crack.using_listview_1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // nullsafe하게 lazy 사용
    val dataList by lazy{
        mutableListOf<String>();
    }

    var adapter : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    private fun setUpUI() {
        setUpListView()
        btnAdd.setOnClickListener {
            dataList.add( edtItem.text.toString())
            adapter?.notifyDataSetChanged()
        }
    }

    private fun setUpListView() {
        // Data 추가
        (0..10).map { dataList.add(it.toString()) }
        // 간단한 adpater생성
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        // adpater 설정
        lstMain.adapter = adapter
        // Item 클릭 이벤트핸들러 설정
        lstMain.setOnItemClickListener { parent, view, position, id ->

            // 삭제하기
            dataList.removeAt(position)
            adapter?.notifyDataSetChanged()
        }
    }
}
