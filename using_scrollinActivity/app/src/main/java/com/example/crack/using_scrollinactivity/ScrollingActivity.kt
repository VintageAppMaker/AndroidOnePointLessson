package com.example.crack.using_scrollinactivity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract.Events
import android.provider.CalendarContract

class ScrollingActivity : AppCompatActivity() {

    val dateStr         = "2018/12/1"
    val WeddingPlaceStr = "서울특별시 용산구 용산2가동 남산공원길 105"
    val geoStr          = "37.5509473,126.9871078"
    val WeddingMsg      = "OO이네 결혼식"

    val MessageStr      = "오랜세월 \n" +
            "서로의 삶을 소리소문없이\n" +
            "함께하게 되더니..\n" +
            "\n" +
            "결국은 결혼까지 하게되었습니다. \n" +
            "두렵고 막막한 것이 \n" +
            "삶이지만 \n" +
            "\n" +
            "둘이 힘을 합쳐\n" +
            "몬스터를 잡듯 \n" +
            "세상을 이겨나가겠습니다. \n" +
            "\n" +
            "오셔서 축복해주시기 \n" +
            "바랍니다. \n" +
            "\n" +
            "신랑: OOO\n" +
            "신부: OOO \n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        toolbar_layout.title = "축복해주세요"

        // API Ver 21이상
        // toolbar_layout.background = getDrawable(R.drawable.wedding)

        txtCount?.text =
                setDayCount()?.let{
                    if (it > 0) "^^"
                    else "${it*-1}일"
                }

        txtMessage?.text = MessageStr.format("백모군", "신모양")


        fab.setOnClickListener { view ->
            Snackbar.make(view, "오시는곳:\n${WeddingPlaceStr}", Snackbar.LENGTH_LONG)
                .setAction("지도찾기", { startMapActivity() }).show()

        }

        anlClock.setOnClickListener {
            addCalendar(2018, 11, 1, 12, 0, WeddingMsg)
        }

    }

    private fun startMapActivity() {
        val uri = Uri.parse("geo:${geoStr}")
        val intent = Intent(android.content.Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun setDayCount() : Long {

        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val date = sdf.parse(dateStr)
        val d =  Date().apply { time = Date().time - date.time }

        return  ( d.time / (60 * 60 * 24 * 1000) )

    }

    private fun addCalendar (y : Int, m : Int, d :Int, hh : Int, mm : Int, msg : String ){
        val beginTime = Calendar.getInstance()
        beginTime.set(y, m, d, hh, mm)

        val endTime = Calendar.getInstance()
        endTime.set(y, m, d, hh, mm)

        val intent = Intent(Intent.ACTION_INSERT).apply {
            setData(Events.CONTENT_URI)
            putExtra(
                CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                beginTime.timeInMillis
            )
            putExtra(
                CalendarContract.EXTRA_EVENT_END_TIME,
                endTime.timeInMillis
            )
            putExtra(Events.TITLE, WeddingMsg)
            putExtra(Events.DESCRIPTION, WeddingMsg)
        }

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



}
