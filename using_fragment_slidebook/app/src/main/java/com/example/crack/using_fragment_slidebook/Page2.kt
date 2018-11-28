package com.example.crack.using_fragment_slidebook
import android.support.v4.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main_two.view.*

class Page2 : Fragment() {

    var nSelection = 0
    var imgUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View? = null
        rootView = inflater.inflate(R.layout.fragment_main_two, container, false)

        arguments?.apply {
            nSelection = getInt(ARG_SECTION_NUMBER)
            imgUrl     = getString(ARG_SECTION_URL)
        }

        MakeEbookView(rootView, nSelection)
        return rootView
    }

    // ebbok view를 만든다.
    fun MakeEbookView(v: View?, nIndx: Int) {
        val fr = (v as FrameLayout)

        fr.section_label.text = "${nIndx} Page"

        // 페이지에 맞는 문자열을 가져온다.
        val sContent = getPageText(
            activity!!.applicationContext,
            nSelection
        )

        fr.txtContent.text = sContent
        // Glide로 이미지 세팅
        Glide.with(activity!!.applicationContext)
            .load(imgUrl)
            .fitCenter()
            .into(fr.imgBanner)

    }

    // 페이지에 맞는 문자열을 가져온다.
    internal fun getPageText(ctx: Context, nIndex: Int): String {
        var sContent = PageReader.readPage(
            activity!!.applicationContext,
            "p${nIndex}.txt"
        )

        return sContent
    }

    // URL로 이동한다.
    internal fun BrowsedUrl(sUrl: String) {
        val i = Intent(Intent.ACTION_VIEW)
        val u = Uri.parse(sUrl)
        i.data = u

        startActivity(i)
    }

    companion object {
        val ARG_SECTION_NUMBER = "section_number"
        val ARG_SECTION_URL    = "section_image_url"
    }



}