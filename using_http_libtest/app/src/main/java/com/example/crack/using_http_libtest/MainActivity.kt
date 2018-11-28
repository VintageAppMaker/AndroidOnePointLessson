package com.example.crack.using_http_libtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.okhttp.OkHttpClient
import kotlinx.android.synthetic.main.activity_main.*
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException
import com.squareup.okhttp.HttpUrl
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.ExecutionException

class MainActivity : AppCompatActivity() {

    private val GOOGLURL  : String? = "https://www.google.co.kr/search"
    private val VINTAGEURL: String? = "http://vintageappmaker.com"
    private val GITHUBURL : String = "https://api.github.com/search/repositories"

    val client by lazy { OkHttpClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // httplib(HTTPS를 기본적으로 지원하지 않는다)
        btnHTTP.setOnClickListener {
            val http = MyHttpRequest(applicationContext)
            val sUrl = "${VINTAGEURL}"

            try {
                val nResultCode = http.execute(sUrl, "GET").get()
                if (nResultCode == MyHttpRequest.REQUEST_FAIL) return@setOnClickListener
                http.string.let{ edtResult.setText(it) }


            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        // okHTTP
        btnOkHTTP.setOnClickListener {
            testOkHTTP(makeOkHttpURL("고양이"))
        }

        // Volly
        btnVolly.setOnClickListener {
            searchGihubProject("${GITHUBURL}?q=vintage")
        }
    }


    private fun makeOkHttpURL(s : String ): String {
        val urlBuilder = HttpUrl.parse(GOOGLURL).newBuilder()
        urlBuilder.addQueryParameter("q", s)
        return urlBuilder.build().toString()
    }

    private fun testOkHTTP(url : String){
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(request: Request?, e: IOException?) {

            }

            override fun onResponse(response: Response?) {
                // 매우중요함. 비동기방식의 통신쓰레드에서 바로 UI를 건들지 못함.
                runOnUiThread { edtResult.setText(response?.body()?.string()) }
            }
        })

    }

    fun searchGihubProject(url : String) {
        val que = Volley.newRequestQueue(this)

        // okHTTP에서도 Request와 Respone 객체가 있으므로
        // com.android.volley에서 사용하는 것을 명시해야 한다.
        // 충돌이 없다(okHTTP를 삭제)면 com.android.volley를 삭제한다.
        val stringReq = StringRequest(
            com.android.volley.Request.Method.GET, url,
            com.android.volley.Response.Listener<String> {
                    response ->

                val jsonArray: JSONArray =
                    response.toString()?.let { JSONObject(it) }.let{ it.getJSONArray("items")}

                var projects: String = ""
                for (i in 0 until jsonArray.length()) {
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    projects = projects + "\n" + jsonInner.get("url")
                }

                /*
                !!비동기임에도 불구하고 UI에 바로 사용할 수 있다!!
                * */
                edtResult.setText ("$projects" )
            },
            com.android.volley.Response.ErrorListener {  })
        que.add(stringReq)
    }
}
