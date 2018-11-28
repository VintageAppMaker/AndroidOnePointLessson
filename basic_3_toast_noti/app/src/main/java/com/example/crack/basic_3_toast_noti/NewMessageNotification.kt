package com.example.crack.basic_3_toast_noti

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
object NewMessageNotification {
    private val NOTIFICATION_TAG = "NewMessage"
    fun notify(
        context: Context,
        exampleString: String, number: Int
    ) {
        val res = context.resources

        // 이것을 삭제하면 메시지에서 이미지가 안보인다.
        val picture =
            BitmapFactory.decodeResource(res, R.mipmap.ic_launcher)

        // 타이틀과 메시지
        val title = "제목입니다."
        val text  = exampleString
        val builder = NotificationCompat.Builder(context)

            // 알림 시, 진동 또는 플래쉬등의 설정
            .setDefaults(Notification.DEFAULT_ALL)

            // 시스템 영역의 아이콘, 타이틀, 메시지 내용
            .setSmallIcon(R.drawable.ic_stat_new_message)
            .setContentTitle(title)
            .setContentText(text)

            // 이 밑에부터는 모두 옵션임.
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setLargeIcon(picture)
            .setTicker(exampleString)
            .setNumber(number)

            // Touch 시, 행동(Intent)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    0,

                    // [TODO] 이곳에 원하는 Intent 만들기
                    // 일반적으로 원하는 Activity를 호출
                    Intent(context, MainActivity::class.java),

                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )

            // 터치시 자동삭제
            .setAutoCancel(true)

        notify(context, builder.build())
    }

    // Notification 만들기
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private fun notify(context: Context, notification: Notification) {
        val nm = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification)
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification)
        }
    }

    // Notification 삭제하기
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    fun cancel(context: Context) {
        val nm = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0)
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode())
        }
    }
}
