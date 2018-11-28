package com.example.crack.using_fragment_slidebook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class Page1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View? = null
        rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val args = arguments
        val url  = args!!.getString(ARG_SECTION_URL)

        MakeWebView(rootView, url)
        return rootView
    }

    // 웹뷰화면을 만든다.
    internal fun MakeWebView(v: View?, sUrl: String) {

        val webview = v!!.findViewById(R.id.webView1) as WebView
        webview.webViewClient = WebClient()
        val set = webview.settings
        set.javaScriptEnabled = true
        set.builtInZoomControls = true
        webview.loadUrl(sUrl)

    }

    internal inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

    companion object {
        val ARG_SECTION_URL = "section_url"
    }


}