package com.example.umc_week7_mission1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_week7_mission1.databinding.ActivityTimesetBinding

class TimeSetActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTimesetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTimesetBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSave.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("minute", viewBinding.etMinute.text.toString().toIntOrNull() ?: -1)
            intent.putExtra("second", viewBinding.etSecond.text.toString().toIntOrNull() ?: -1)
            startActivity(intent)
        }
    }

}