package com.example.umc_week7_mission1

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.example.umc_week7_mission1.databinding.ActivityMainBinding
import com.example.umc_week7_mission1.databinding.ActivityTimesetBinding
import java.util.jar.Attributes
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var soundPool: SoundPool
    private var soundID: Int = 0
    var started = false // 시간이 진행중임을 알리는 변수
    var minute = -1
    var second = -1
    var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnStart.setOnClickListener { start() }
        viewBinding.btnPause.setOnClickListener { pause() }
        viewBinding.btnStop.setOnClickListener { stop() }

        viewBinding.btnTimeset.setOnClickListener {
            var intent = Intent(this, TimeSetActivity::class.java)
            startActivity(intent)
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ // android studio의 version에 맞는 Soundpool 설정
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .build()
            soundPool = SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build()
        }else{
            soundPool =  SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        }
        soundID = soundPool.load(this, R.raw.bell, 1)
    }

    override fun onResume() {
        super.onResume()
        minute = intent?.getIntExtra("minute", -1) ?: -1 // ?: 는 인텐트 객체가 null인 경우 고려
        second = intent?.getIntExtra("second", -1) ?: -1
    }

    val SET_TIME = 51
    val RESET = 52
    val TOAST = 53

    val handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                SET_TIME -> {
                    viewBinding.txtTimer.text = formatTime(msg.arg1)
                }
                RESET -> {
                    viewBinding.txtTimer.text = "00 : 00"
                }
                TOAST -> {
                    // 메소드의 매개변수 의미
                    // leftVolume : 왼쪽 스피커의 볼륨을 나타내는 값
                    // rightVolume : 오른쪽 스피커의 볼륨을 나타내는 값
                    soundPool.play(soundID, 1.0f, 1.0f, 1, 0, 1.0f)
                    viewBinding.txtTimer.setTextColor(getColor(R.color.red))
                    Toast.makeText(applicationContext, "설정한 알람시간이 되었습니다 !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    fun start(){
        started = true // sub Thread
        thread(start = true) { // start의 의미 : 스레드에게 시작하라는 명령
            while(started){
                Thread.sleep(1000)
                if(!started) break
                if(minute == -1 && total == second){
                    val msg2 = Message()
                    msg2.what = TOAST
                    handler.sendMessage(msg2)
                    break
                }else if(total/60 == minute && total%60 == second){
                    val msg3 = Message()
                    msg3.what = TOAST
                    handler.sendMessage(msg3)
                }
                total += 1
                val msg = Message()
                msg.arg1 = total
                msg.what = SET_TIME
                handler.sendMessage(msg)
            }
        }
    }

    fun pause(){
        started = false
    }

    fun stop(){
        started = false
        minute = -1
        second = -1
        total = 0
        viewBinding.txtTimer.setTextColor(getColor(R.color.black))
        viewBinding.txtTimer.text = "00 : 00"
    }

    fun formatTime(time:Int) : String{ // int 형으로 데이터가 넘어오면 string으로 변환할 것
        var minute = String.format("%02d", time/60)
        var second = String.format("%02d", time%60)
        return "$minute : $second"
    }
}