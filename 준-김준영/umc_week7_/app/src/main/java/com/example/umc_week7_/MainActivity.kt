package com.example.umc_week7_

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ProgressBar
import com.example.umc_week7_.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnMain.setOnClickListener {
            progressDialog = ProgressDialog(this)
            with(progressDialog){
                max = 100
                setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                setTitle("다운로드")
                setMessage("잠시만 기다려주세요 ... ")
                setCancelable(false) // 중간에 다운로드 취소 불가능
                show()
            }

            var handler: Handler = object : Handler(Looper.getMainLooper()){
                override fun handleMessage(msg: Message) {
                    progressDialog.incrementProgressBy(10) // progressDialog를 10씩 증가시키는 기능
                }
            } // 모든 스레드는 개별 Looper를 가지고 있다. 핸들러가 백그라운드 스레드에 의해 만들어진 백그라운드 Looper를 사용할 경우
            // 액티비티가 종료되도 메세지 큐에 메세지가 존재한다. 즉 액티비티가 종료됨에따라 핸들러도 종료하기 위해서 메인스레드의 Looper를 사용하게한다.(메모리누수 방지)

            Thread{
                try{
                    while(progressDialog.progress <= progressDialog.max){
                        Thread.sleep(200) // 0.2초마다 핸들러가 루프에 메세지를 입력받아 10씩 증가시키는 것
                        handler.sendMessage(handler.obtainMessage())
                        if(progressDialog.progress >= progressDialog.max){
                            progressDialog.dismiss() // progressDialog 종료
                        }
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }.start()
        }


    }
}