package com.example.umc_week7_mission2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.umc_week7_mission2.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var elapsedTime = 0
    private var isPlaying = true
    private var job: Job? = null

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var sbBar : SeekBar
    private lateinit var mdPlayer : MediaPlayer
    private lateinit var play : ImageView
    private lateinit var pause : ImageView
    private lateinit var playTime : TextView
    private lateinit var totalTime : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        sbBar = viewBinding.sbPlay
        play = viewBinding.ivPlay
        pause = viewBinding.ivPause
        playTime = viewBinding.tvPlayTime
        totalTime = viewBinding.tvTotalTime

        mdPlayer = MediaPlayer.create(this, R.raw.music)
        totalTime.text = formatTime(mdPlayer.duration)
        playTime.text = formatTime(mdPlayer.currentPosition)
        sbBar.max = mdPlayer.duration
        sbBar.progress = mdPlayer.currentPosition

        play.setOnClickListener {
            if(isPlaying){
                viewBinding.ivPlay.setImageResource(R.drawable.btn_playing)
                playMusic()
                isPlaying = false
            }
            else {
                viewBinding.ivPlay.setImageResource(R.drawable.btn_play)
                mdPlayer.pause()
                isPlaying = true
            }
        }
        pause.setOnClickListener { pauseMusic() }

        sbBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(sbBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mdPlayer.seekTo(progress)
                    if(progress == mdPlayer.duration) pauseMusic()
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }


    private fun playMusic() {
        mdPlayer.start()
        var currentPosition = mdPlayer.currentPosition
        job = lifecycleScope.launch(Dispatchers.IO) {
            while (isActive) {
                if(!mdPlayer.isPlaying){
                    break;
                }
                withContext(Dispatchers.Main){
                    playTime.text = formatTime(currentPosition)
                    elapsedTime = mdPlayer.duration - currentPosition
                    totalTime.text = formatTime(elapsedTime)
                    sbBar.progress = currentPosition
                }
                delay(1000)
                currentPosition = mdPlayer.currentPosition
            }
        }
        job?.invokeOnCompletion { job = null }
    }

    fun pauseMusic(){
        mdPlayer.stop()
        mdPlayer.reset()
        isPlaying = true
        play.setImageResource(R.drawable.btn_play)
        time = 0
        totalTime.text = formatTime(mdPlayer.duration)
        playTime.text = "00:00"
        sbBar.progress = 0
        mdPlayer = MediaPlayer.create(this, R.raw.music)
    }

    fun formatTime(progress: Int): String {
        val totoalSecond = progress / 1000
        val minutes = totoalSecond / 60
        val seconds = totoalSecond % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        mdPlayer.stop()
        mdPlayer.release()
    }

}