package com.example.crack.using_fragment_slidebook

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        AddPages()
        container.adapter = mSectionsPagerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    // 페이지를 추가한다.
    private fun AddPages() {
        // 이 부분을 좀 더 동적으로 처리하면 Ebook 백본이 된다.
        val pages = listOf(
            ContentInfo("YNU3OWaxb1A&t=122s",
                "https://i.ytimg.com/vi/dG9Hfl5Fr9Q/hqdefault.jpg?sqp=" +
                        "-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=" +
                        "AOn4CLAJNAb-1Oa5GkwaVaiYjVXxNw6Kyg"),

            ContentInfo("6tBYeodOYes",
                "https://i.ytimg.com/vi/kBbSo98aIe0/hqdefault.jpg?" +
                        "sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ" +
                        "==&rs=AOn4CLAirvHDI6nSh0Ac1PPpMoDELwfEIw"),

            ContentInfo("gSFxyfkB4n4&t=13s",
                "https://i.ytimg.com/vi/gSFxyfkB4n4/hqdefault.jpg?sqp=" +
                        "-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=" +
                        "AOn4CLCloeZRuTedrJHFptLTnAgmR0K7MA")
        )

        for (i in (1.. pages.size)) {
            val p1 = Page1()
            p1.arguments = Bundle().apply {
                putString(Page1.ARG_SECTION_URL, "${getBaseUrl()}${pages.get( i -1 ).keyword}")
            }
            mSectionsPagerAdapter!!.AddFragment(p1)

            val p2 = Page2()
            p2.arguments = Bundle().apply {
                putString(Page2.ARG_SECTION_URL, pages.get( i - 1 ).imgUrl)
                putInt(Page2.ARG_SECTION_NUMBER, i)
            }
            mSectionsPagerAdapter!!.AddFragment(p2)
        }

    }

    private fun getBaseUrl() = "https://www.youtube.com/watch?v="

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        var mFragment = ArrayList<Fragment>()

        // 추가하기
        fun AddFragment(f: Fragment) {
            mFragment.add(f)

        }

        override fun getItem(position: Int): Fragment {
            return mFragment.get(position)
        }

        override fun getCount(): Int {
            return mFragment.size
        }
    }

}
