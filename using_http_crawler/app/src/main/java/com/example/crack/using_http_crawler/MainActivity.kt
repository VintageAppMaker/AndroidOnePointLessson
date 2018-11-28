package com.example.crack.using_http_crawler

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_layout.view.*
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    private var requestURL: String? = "http://vintageappmaker.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    // 버튼을 가져오고 onClick 이벤트핸들러를 지정하는 메소드
    private fun setUpUI() {
        edtUrl.setText(requestURL)
        btnOk!!.setOnClickListener {
            // 이전에 추가된 View를 모두삭제
            lnImageList.removeAllViews()

            // 함수형 프로그래밍에서는 싫어하는 코드
            requestURL = edtUrl.text.toString()

            // Android에서는 절대로 서버와 통신을 "바로 사용할 수 없다"
            // 즉, Activiy와는 다른 메모리 영역인 (Thread)기반의 클래스를
            // 이용하여 통신을 해야 한다. - 주로 AsyncTask 클래스를 활용한다.
            // 여기서는 동기적으로 기다린다.
            val tsk = getTask()
            tsk.execute().get()

            tsk.lstUrl?.map {
                addUrlList(it, { ctx, url, ln ->

                    val downloadUrl = if ( url.indexOf("http") != 0) requestURL + url else url
                    ln.txtMessage.text = downloadUrl
                    try{
                        // Glide로 추가
                        Glide.with(ctx)
                            .load(downloadUrl)
                            .fitCenter()
                            .into(ln.imgLink)

                    } catch (e: Exception){ Log.d("TEST", e.toString())}

                })
            }
        }
    }

    // 함수형 프로그래밍 스타일
    private fun addUrlList(
        imgUrl: String, func: (Context, String, LinearLayout) -> Unit) {

        // XML 화면을 가져와서 Linearlayout을 만든다.
        val layoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = layoutInflater.inflate(R.layout.add_layout, null) as LinearLayout

        // 넘겨진 함수를 수행
        func(applicationContext, imgUrl, layout)

        // LinearLayout에생성된 TextView 추가 및 애니메이션
        lnImageList.addView(layout)
    }


    // www.daum.net 홈페이지 소스를 가져와 a tag의 정보를 보여주는 메소드
    @Throws(Exception::class)
    fun getHomepageInfo(): List<String> {

        val lst = mutableListOf<String>()

        // Jsop을 이용해 URL의 HTML 소스를 가져온다.
        val document = Jsoup.connect(requestURL).get()

        if (null != document) {
            // img tag를 가져온다.
            val elements = document.select("img[src]")

            // 존재하는 갯수만큼 ....
            for (i in 0 until elements.size) {
                // 링크정보
                lst.add ( elements.get(i).attr("src") )
            }
        }

        return lst
    }

    // 서버통신을 한다면 AsyncTask 클래스를 주로 이용한다.
    // AsyncTask는 외부에서 execute() 메소드가 호출되는 순간
    // onPreExecute -> doInBackground -> onPostExecute 순서로 실행된다.
    inner class getTask : AsyncTask<String, String, String>() {
        var lstUrl : List <String>? = null

        override fun onPreExecute() {
            super.onPreExecute()
        }

        protected override fun doInBackground(vararg p0: String?): String {
            try {
                // 위를 주석처리 해결책
                lstUrl = getHomepageInfo()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }

        protected override fun onPostExecute(o: String?) {
            super.onPostExecute(o)
        }

    }
}
