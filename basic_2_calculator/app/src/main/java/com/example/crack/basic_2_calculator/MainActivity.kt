package com.example.crack.basic_2_calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 화면이 생성될 때 실행되는 함수(메소드)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*=======================================*/
        // TO-Do: 여기서부터 우리가 코딩!
        /*=======================================*/

        // + 버튼을 눌렀을 때(일반적인 스타일)
        btnPlus.setOnClickListener{

            // 입력받은 값이 null인지 채크
            if (firstNumber.text == null || secondNumber.text == null) {
                return@setOnClickListener
            }

            // 입력받은 값이 ""인지 채크
            if (firstNumber.text.length == 0 || secondNumber.text.length == 0) {
                return@setOnClickListener
            }

            // 숫자값을 가져오기
            var first   = firstNumber.text.toString()
            var second  = secondNumber.text.toString()

            // 문자열을 숫자로 변형하는 방법은 ?
            // toInt()
            var result = addNumber(first.toInt(), second.toInt())
            txtResult.setText("$result")

        }

        // - 버튼을 눌렀을 때(함수형 프로그래밍 스타일)
        btnMinus.setOnClickListener {

            // 입력받은 값을 검증
            val lstCheck = listOf(firstNumber, secondNumber)
            lstCheck.map { if (it == null) return@setOnClickListener else it }
               .map { if (it.text.length < 1) return@setOnClickListener else it}


            // 숫자값을 가져오기
            val lstNumber = lstCheck.map { it.text.toString().toInt() }
            lstNumber.let {
                calculate(::subNumber,
                    it.get(0), it.get(1))
                    .let { txtResult.text = "${it}" }
            }

        }

    }

    // 빼기 함수(메소드)
    private fun subNumber(i: Int, i1: Int) : Int{
        return i - i1;
    }

    // 더하기 함수(메소드)
    private fun addNumber(i: Int, i1: Int) : Int{
        return i + i1;
    }

    // 함수형 예제를 위한 계산함수
    private fun calculate(pFunc : (Int, Int)->Int,  num1 : Int, num2 : Int) : Int {
        return pFunc(num1, num2)
    }
}