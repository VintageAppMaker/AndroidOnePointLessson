package com.example.crack.using_webview_blogbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        setUpUI()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private inner class WebClient : WebViewClient() {

        // URL 호출하기 전...
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }

        // webpage를 모두 읽었을 때,
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
        }
    }

    private fun setUpUI() {

        // 무조건해야 한다. 웹페이지 진행상황을 관리하는 클래스
        wbMain.webViewClient = WebClient()

        // 세팅을 가져오고 설정한다.
        val set = wbMain.getSettings()

        // 자바스크립틀을 사용가능하게 하고 zoom을 false한다.
        set.setJavaScriptEnabled(true)
        set.setBuiltInZoomControls(false)

        // 이동한다.
        wbMain.loadUrl("http://www.vintageappmaker.com/")
        toolbar.title = "공식페이지"
    }

    // 백버튼을 눌렀을 때
    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {

            // history back 구현
            if (wbMain.canGoBack()) {
                wbMain.goBack()
            } else {
                super.onBackPressed()
            }
        }
    }

    // 메뉴가 선택되었을 떄,
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_home) {
            wbMain.loadUrl("http://www.vintageappmaker.com/")

        } else if (id == R.id.nav_tumbler) {
            wbMain.loadUrl("http://vintageappmaker.tumblr.com/")

        } else if (id == R.id.nav_naver) {
            wbMain.loadUrl("http://blog.naver.com/adsloader")

        } else if (id == R.id.nav_map) {
            wbMain.loadUrl("https://www.google.co.kr/maps/place/37%C2%B028'35.0%22N+126%C2%B058'51.4%22E/@37.476381,126.9788637,17z/data=!3m1!4b1!4m2!3m1!1s0x0:0x0")

        } else if (id == R.id.nav_send) {
            sendEmail()

        } else if (id == R.id.nav_apps) {
            shareApps()

        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    // App 공유
    private fun shareApps() {

        val marketLaunch = Intent(Intent.ACTION_VIEW)
        marketLaunch.data = Uri.parse("market://search?id=Vintage appMaker")
        startActivity(marketLaunch)
    }

    // 이메일 공유
    private fun sendEmail() {
        val it = Intent(Intent.ACTION_SEND)
        it.type = "plain/text"

        val tos = arrayOf("aaa@naver.com")
        it.putExtra(Intent.EXTRA_EMAIL, tos)
        it.putExtra(Intent.EXTRA_SUBJECT, "문의메일입니다.")
        it.putExtra(Intent.EXTRA_TEXT, "[자동생성]")
        startActivity(it)
    }
}
