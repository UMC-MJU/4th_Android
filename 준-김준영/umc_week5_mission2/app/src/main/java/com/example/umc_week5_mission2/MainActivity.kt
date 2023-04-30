package com.example.umc_week5_mission2

import android.app.Activity
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.content.SyncRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_week5_mission2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var memoList: ArrayList<Memos>
    private lateinit var memoAdapter: MemosAdapter
    private var REQUEST_CODE_MEMO = 100

    fun getMemoList(): ArrayList<Memos>{
        return memoList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memoList = arrayListOf()
        memoAdapter = MemosAdapter(memoList)

        val addBtn = binding.btnMain
        addBtn.setOnClickListener {
            val intent = Intent(this, MemoWriteActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_MEMO)
        }

        binding.rvMemo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMemo.setHasFixedSize(true) //고정된 크기를 지는 기능
        binding.rvMemo.adapter = memoAdapter

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_MEMO && resultCode == Activity.RESULT_OK) {
            val memo = data?.getStringExtra("memo")
            val memoIndex = data?.getIntExtra("memoIndex",-1)
            val memoIndex_value = data?.getStringExtra("memoIndex_value")

            if(memoIndex == -1){
                memoList.add(Memos(R.drawable.note, memo.toString()))
            }else{
                memoList[memoIndex!!].txtview = memoIndex_value.toString()
            }

            memoAdapter.notifyDataSetChanged()
        }
    }

}