package com.example.crack.using_http_imageloader

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 인터넷에 있는 이미지의 주소
    private val imgURL  = "https://avatars3.githubusercontent.com/u/31234716?s=460&v=4"
    private val imgURL2 = "https://avatars2.githubusercontent.com/u/12086377?s=460&v=4"

    private fun startFlipAnimation() {
        vfFlip.setAutoStart(true)
        vfFlip.setFlipInterval(4000)
        vfFlip.startFlipping()
    }

    // 함수형 프로그래밍 스타일
    private fun addImageWithLibrary(
        imgUrl: String, func: (Context, String, ImageView) -> Unit) {
        // 동적으로 이미지 생성 및 scaleType 설정
        val image = ImageView(applicationContext)
        image.scaleType = ImageView.ScaleType.FIT_XY

        // 넘겨진 함수를 수행
        func(applicationContext, imgUrl, image)

        // vfFlip(Flipper)에 생성된 image 추가 및 애니메이션
        vfFlip.addView(image)
        startFlipAnimation()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGlide.setOnClickListener {
            addImageWithLibrary(imgURL, { ctx, url, image ->
                // Glide로 추가
                Glide.with(ctx)
                    .load(url)
                    .fitCenter()
                    .into(image)
            })
        }

        btnPicasso.setOnClickListener {
            addImageWithLibrary(imgURL2, { ctx, url, image ->
                // picasso로 추가
                Picasso.get().load(url).into(image)
            })
        }
    }
}
