package com.example.crack.using_listview_2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // nullsafe하게 lazy 사용
    val dataList by lazy{
        mutableListOf<WebInfo>();
    }

    var adapter : ListViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    private fun setUpUI() {
        // 데이터추가(함수형 프로그래밍 스타일)
        (0..50).forEach{
            dataList.add(
                WebInfo(
                    if (it % 2 == 0 )
                        "https://avatars3.githubusercontent.com/u/31234716?s=460&v=4"
                    else "https://avatars2.githubusercontent.com/u/12086377?s=460&v=4",
                    "${it}번 추가\n웹사이트 정보입니다. ") )
        }

        adapter = ListViewAdapter(applicationContext, dataList)
        lstMain.adapter = adapter
        lstMain.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                applicationContext,
                dataList.get(position).let { it.desc },
                Toast.LENGTH_LONG).show()
        }
    }

    // Item에 보여줄 정보를 담을 Class
    data class WebInfo(var image : String, var desc : String )
    class ListViewAdapter(c : Context, l : MutableList<WebInfo>) : BaseAdapter(){
        var lstData : MutableList<WebInfo>
        var ctx : Context

        init {
            lstData  = l
            ctx      = c
        }

        // Control 정보를 담을 Class
        inner class ViewHolder {
            var txtMessage  : TextView?  = null
            var imgLink     : ImageView? = null
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

                // 새롭게 만든정보를 tag에 저장하고 Android에 넘긴다.
                view!!.setTag(holder)

            } else {
                // tag에 있는 정보를 holder로 가져온다.
                holder = view!!.getTag() as ViewHolder
            }

            holder.txtMessage!!.text = lstData.get(position).let{ it.desc }

            // Glide로 동적이미지 로딩
            Glide.with(ctx)
                .load(lstData.get(position).let{ it.image })
                .fitCenter()
                .into(holder.imgLink)

            return view
        }

        // 데이터의 크기를 요청하면 넘긴다. 필요함.
        override fun getCount(): Int {
            return lstData.size
        }

        // Index의 아이템을 요청할 때 넘긴다. 필요할 때만 구현함
        override fun getItem(position: Int): Any {
            return lstData.get(position)
        }

        // 고유아이디를 요청하면 넘긴다. 필요할 때만 구현함
        override fun getItemId(position: Int): Long {
            return 0
        }
    }
}
