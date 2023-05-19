package com.example.umc_week8_1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.umc_week8_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSave.setOnClickListener {
            savePref()
        }

        viewBinding.btnLoad.setOnClickListener {
            loadPref()
        }
    }

    private fun savePref(){
        val sharedPreferences = getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE) // 키값은 KEY_PREFS, 모드는 앱 내에서만 사용가능
        val editor = sharedPreferences.edit() // 에디터 객체 인스턴스 생성

        editor.putInt(KEY_Quality, viewBinding.rgQuality.checkedRadioButtonId) // radio id 값을 KEY값에 저장
        editor.putInt(KEY_MUSIC, viewBinding.sbMusic.progress) // seekBar에 progress를 KEY값에 저장
        editor.putBoolean(KEY_AUTO, viewBinding.switchAuto.isChecked) // 체크형태의 boolean형태로 KEY값에 저장

        editor.apply() // 실제 값이 저장되는 순간
        Toast.makeText(applicationContext, "저장완료", Toast.LENGTH_SHORT).show()
    }

    private fun loadPref(){
        val sharedPreferences = getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE)
        if(sharedPreferences.contains(KEY_Quality)){ // sharedPreferences 자체가 만들어지지 않은 경우도 있기 떄문에 조건문 이용 (무엇을 적어도 상관없음)
            val qualityValue = sharedPreferences.getInt(KEY_Quality, 0)
            val musicVolume = sharedPreferences.getInt(KEY_MUSIC, 0)
            val autoValue = sharedPreferences.getBoolean(KEY_AUTO, true)

            viewBinding.rgQuality.check(qualityValue) // 각각의 저장된 값들을 불러옴
            viewBinding.sbMusic.progress = musicVolume
            viewBinding.switchAuto.isChecked = autoValue

            Toast.makeText(applicationContext, "저장된 설정 값을 불러옵니다.", Toast.LENGTH_SHORT).show()
        }

    }

    companion object{ // 저장할때 사용하는 key값을 companion object로 정함 (이 방식이 더 안정적임)
        private const val KEY_PREFS = "music_settings"
        private const val KEY_Quality = "music_quality"
        private const val KEY_MUSIC = "music_volume"
        private const val KEY_AUTO = "music_auto"
    }
}