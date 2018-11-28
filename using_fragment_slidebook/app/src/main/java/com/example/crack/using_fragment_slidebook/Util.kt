package com.example.crack.using_fragment_slidebook

import java.io.IOException

import android.content.Context
import kotlin.text.Charsets.UTF_8

// asset에서 파일을 읽는다.
object PageReader {
    @Throws(IOException::class)
    fun readPage(ctx: Context, sPageFile: String): String {

        val `is` = ctx.assets.open(sPageFile)
        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, UTF_8)
    }
}

data class ContentInfo(var keyword : String, var imgUrl : String)