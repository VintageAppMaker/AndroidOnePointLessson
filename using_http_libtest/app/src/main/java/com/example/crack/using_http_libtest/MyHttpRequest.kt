package com.example.crack.using_http_libtest

/**
 * Created by crack on 2018-02-10.
 */

import android.content.Context
import android.os.AsyncTask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MyHttpRequest(internal var ctx: Context) : AsyncTask<String, Int, Int>() {
    internal var result: StringBuilder? = null

    val string: String
        get() = result!!.toString()

    init {
        result = StringBuilder()
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg arg0: String): Int? {
        var conn: HttpURLConnection? = null

        try {
            val url = URL(arg0[0])
            conn = url.openConnection() as HttpURLConnection

            // 연결이 안되었으면 나가삼.
            if (conn == null) return -1

            conn.connectTimeout = CONNECTION_TIME_OUT
            conn.requestMethod = arg0[1]        // GET or POST
            conn.doInput = true
            conn.doOutput = true

            val resCode = conn.responseCode

            // HTTP를 제대로 가져왔다면...
            if (resCode == HttpURLConnection.HTTP_OK) {

                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                var line: String? = null

                while (true) {

                    line = reader.readLine()

                    if (line == null) {
                        break
                    }
                    result!!.append(line)
                    result!!.append("\n")
                }

                reader.close()
                conn.disconnect()

            } else {
                return REQUEST_FAIL
            }


        } catch (ex: Exception) {
            ex.printStackTrace()
            return REQUEST_FAIL
        }

        return REQUEST_OK
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
    }

    companion object {

        // 연결타임아웃과 HTTP를 읽어온 성공여부를 정의
        var CONNECTION_TIME_OUT = 1000 * 5
        var REQUEST_OK = 0
        var REQUEST_FAIL = -1
    }
}
