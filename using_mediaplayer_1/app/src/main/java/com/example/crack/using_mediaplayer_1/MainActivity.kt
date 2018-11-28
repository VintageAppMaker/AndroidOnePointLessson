package com.example.crack.using_mediaplayer_1
import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var mediaRecorder: MediaRecorder?  = null
    internal var mediaPlayer: MediaPlayer? = null

    // 최초요청이 있으면 초기화
    internal val sFileName by lazy{
        Environment.getExternalStorageDirectory().absolutePath + "/" + "MyRecording.3gp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 4. 퍼미션허락을 받고
        // 미디어를 레코딩함
        btnRecord.setOnClickListener{
            if(chkPermission() == false ){
                reqPermission()
                return@setOnClickListener
            }
            setUpMediaRecorder()
            mediaRecorder!!.prepare()
            mediaRecorder!!.start()

        }

        // 5.레코딩을 종료하며 저장함
        btnRecordSave.setOnClickListener {
            mediaRecorder!!.stop()
        }

        // 6. 레코딩된 내용을 play
        btnPlay.setOnClickListener {
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setDataSource(sFileName)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()

        }
    }

    // 1. 퍼미션 요청의 결과를 받는 핸들러 (요청의 결과를 받았을 때...)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            RequestPermissionCode -> if (grantResults.size > 0) {

                val StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val RecordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED

                if (StoragePermission && RecordPermission) {
                    Toast.makeText(this@MainActivity, "Permission 허락됨", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Permission 거부됨", Toast.LENGTH_LONG).show()

                }
            }
        }
    }

    // 2-1. 퍼미션 체크
    fun chkPermission(): Boolean {

        val r  = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val r2 = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.RECORD_AUDIO)

        return r == PackageManager.PERMISSION_GRANTED && r2 == PackageManager.PERMISSION_GRANTED
    }

    // 2-2. 퍼미션 요청
    fun reqPermission(){
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO),
            RequestPermissionCode)
    }

    // 3. 미디어레코더를 초기화
    fun setUpMediaRecorder() {

        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)

        mediaRecorder!!.setOutputFile(sFileName)

    }

    // 0. 퍼미션 요청과 허락을 처리할 상수값
    companion object {
        val RequestPermissionCode = 1000
    }
}
