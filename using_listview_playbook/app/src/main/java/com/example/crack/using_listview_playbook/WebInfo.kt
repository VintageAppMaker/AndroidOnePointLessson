package com.example.crack.using_listview_playbook

import android.content.Context

// Item에 보여줄 정보를 담을 Class
data class WebInfo(var music : String , var image : String, var desc : String )

fun getWebInfoList(ctx: Context, sPageFile: String): MutableList<WebInfo> {

    val mList : MutableList<WebInfo> = mutableListOf<WebInfo>()

    val `is` = ctx.assets.open(sPageFile)
    val size = `is`.available()
    val buffer = ByteArray(size)
    `is`.read(buffer)
    `is`.close()

    // 파일 인코딩이 어떤 상태인지 반드시 알아야 합니다.
    // 설정안하면 괴상한 문자를 만나게 될 것입니다.
    val sText = String(buffer)

    // 문자열파싱 music|image|설명
    // 1. \n으로 파싱
    val sLine = sText.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    for (i in sLine.indices) {
        val s = sLine[i]

        // 2. "|"로 파싱 split 사용할 떄 반드시 \를 입력했어야 했나?
        // 이것이 없으면 안되더라..--;
        val sInfo = s.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val m = WebInfo(
            music = sInfo[0],
            image = sInfo[1],
            desc =  sInfo[2]
        )

        mList.add(m)
    }

    return mList
}