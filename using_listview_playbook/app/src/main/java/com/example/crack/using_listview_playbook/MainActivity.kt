package com.example.crack.using_listview_playbook

import android.content.Context
import android.media.AudioManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    var dataList : MutableList<WebInfo>? = null
    var adapter : ListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHideTitle()
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    private fun setHideTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()!!.hide()
    }

    private fun setUpUI() {
        // 데이터추가
        dataList = getWebInfoList(applicationContext, "list.txt")
        adapter = ListViewAdapter(applicationContext, dataList)
        lstMain.adapter = adapter
    }

    class ListViewAdapter(c : Context, l : MutableList<WebInfo>?) : BaseAdapter(){
        var lstData : MutableList<WebInfo>?
        var ctx : Context

        // 미디어플레이어
        var mPlayer :MediaPlayer? = null

        init {
            lstData  = l
            ctx      = c
        }

        // Control 정보를 담을 Class
        inner class ViewHolder {
            var txtMessage  : TextView?  = null
            var imgLink     : ImageView? = null
            var btnPlay     : ImageView? = null
            var btnStop     : ImageView? = null

        }

        // Android가 요청할 경우, Item을 보여주는 메소드
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            // Android에서 넘겨받은 View와 View가 null일경우 생성할 holder
            var view = convertView
            val holder: ViewHolder

            if(view == null){

                // View가 없으니 View의 정보를 담을 holder를 생성 및 대입
                holder = ViewHolder()
                view   = LayoutInflater.from(ctx)?.inflate(R.layout.item_layout, null)
                holder.txtMessage = view!!.findViewById(R.id.txtMessage)
                holder.imgLink    = view!!.findViewById(R.id.imgLink)
                holder.btnPlay    = view!!.findViewById(R.id.btnPlay)
                holder.btnStop    = view!!.findViewById(R.id.btnStop)

                // 새롭게 만든정보를 tag에 저장하고 Android에 넘긴다.
                view!!.setTag(holder)

            } else {
                // tag에 있는 정보를 holder로 가져온다.
                holder = view!!.getTag() as ViewHolder
            }

            holder.txtMessage!!.text = lstData!!.get(position).let{ it.desc }

            // Glide로 동적이미지 로딩
            Glide.with(ctx)
                .load(lstData!!.get(position).let{ it.image })
                .fitCenter()
                .into(holder.imgLink)

            // 이벤트핸들러 등록(버그있음)
            holder.btnPlay!!.setOnClickListener{
                Toast.makeText(ctx, lstData!!.get(position).desc + " play", Toast.LENGTH_LONG).show()
                if(mPlayer != null){
                    mPlayer?.stop();
                    mPlayer = null;
                }

                mPlayer = MediaPlayer()?.apply{
                    setDataSource(lstData!!.get(position).music)
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                    prepare()
                    start()
                }
            }
            holder.btnStop!!.setOnClickListener{
                Toast.makeText(ctx, lstData!!.get(position).desc + " stop", Toast.LENGTH_LONG).show()
                mPlayer?.stop()
            }

            return view
        }

        // 데이터의 크기를 요청하면 넘긴다. 필요함.
        override fun getCount(): Int {
            return lstData!!.size
        }

        // Index의 아이템을 요청할 때 넘긴다. 필요할 때만 구현함
        override fun getItem(position: Int): Any {
            return lstData!!.get(position)
        }

        // 고유아이디를 요청하면 넘긴다. 필요할 때만 구현함
        override fun getItemId(position: Int): Long {
            return 0
        }
    }
}
