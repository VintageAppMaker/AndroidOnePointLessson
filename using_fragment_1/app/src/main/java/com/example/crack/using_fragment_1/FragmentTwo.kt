package com.example.crack.using_fragment_1

import android.os.Bundle
import android.app.Fragment
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_two.view.*

class FragmentTwo : Fragment(){

    // View가 만들어질 때
    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Fragment를 포함하고 있는 부모가 있다면
        // fragment_one를 기준으로 화면을 만들어
        // Linearlayout을 반환한다.
        return if (container == null) {
            null
        } else inflater!!.inflate(R.layout.fragment_two, container, false) as LinearLayout;
    }

    // 화면이 활성화 될 때, 화면을 구성한다.
    override fun onStart() {
        super.onStart()

        setUpUI()
    }

    private fun setUpUI() {
        val v = view as LinearLayout
        v.txtMessage.text = "Fragment two 입니다."
        v.btnClick.setOnClickListener {
            v.txtMessage.setBackgroundColor(Color.parseColor("#FF00F0"))
            v.txtMessage.text = "Click"
        }
    }
}