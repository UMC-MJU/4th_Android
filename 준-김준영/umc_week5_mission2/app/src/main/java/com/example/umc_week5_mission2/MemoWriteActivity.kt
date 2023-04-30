package com.example.umc_week5_mission2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_week5_mission2.MainActivity
import com.example.umc_week5_mission2.databinding.ActivityWriteBinding

class MemoWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memoIndex = intent.getIntExtra("memoIndex", -1)
        val saveBtn = binding.btnSave
        val currentValue = intent.getStringExtra("currentValue") ?: ""

        saveBtn.setOnClickListener {
            val data = binding.etTxt.text.toString()
            val resultIntent = Intent()

            if(memoIndex == -1){
                resultIntent.putExtra("memo", data)
            }else{
                binding.etTxt.setHint(currentValue.toString())
                binding.btnSave.setText("수정완료")
                resultIntent.putExtra("memoIndex", memoIndex)
                resultIntent.putExtra("memoIndex_value", data)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}