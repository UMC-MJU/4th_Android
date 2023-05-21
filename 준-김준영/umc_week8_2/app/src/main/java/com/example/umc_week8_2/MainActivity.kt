package com.example.umc_week8_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.umc_week8_2.databinding.ActivityMainBinding
import com.example.umc_week8_2.databinding.FavoriteBinding
import com.example.umc_week8_2.databinding.ItemRecyclerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    lateinit var helper:RoomHelper
    val memoList = mutableListOf<RoomMemo>()
    lateinit var memoDao:RoomMemoDAO
    lateinit var memoAdapter:RecyclerAdapter


    @Subscribe
    fun UpdateStar(event: getUpdateStar){
        val no = event.no
        val content = event.content
        val date = event.date
        val star = event.star
        val fav = event.fav

        val intent = Intent(this, Fav_Activity::class.java)
        intent.putExtra("update", true)
        intent.putExtra("no", no)
        intent.putExtra("content", content)
        intent.putExtra("date", date)
        intent.putExtra("star", star)
        intent.putExtra("fav", fav)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        EventBus.getDefault().register(this)
        Log.d("MAIN", "ON")
        //
        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db") // 데이터베이스 생성
            .addMigrations(RoomHelper.MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
        memoDao = helper.roomMemoDao()
        memoAdapter = RecyclerAdapter(memoList, memoDao)

        refreshAdapter()

        with(binding){
            rvMemo.adapter = memoAdapter
            rvMemo.layoutManager = LinearLayoutManager(this@MainActivity) // 그냥 this만 쓰면 안된다 -> with로 감싸고 있기 때문

            btnSave.setOnClickListener {
                val content = etMemo.text.toString()
                if(content.isNotEmpty()){
                    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0) // it을 사용하여 view 참조 수정
                    val datetime = System.currentTimeMillis()
                    val memo = RoomMemo(content, datetime, 0, 0)
                    etMemo.setText("")
                    insertMemo(memo)
                }else{
                    Toast.makeText(applicationContext, "내용을 입력하세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnFav.setOnClickListener {
            startActivity(Intent(this@MainActivity, Fav_Activity::class.java))
        }

    }



    fun insertMemo(memo:RoomMemo){
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.insert(memo)
            refreshAdapter()
        }
    }

    fun refreshAdapter(){ // 메모리스트를 갱신하는 부분
        CoroutineScope(Dispatchers.IO).launch {
            val updateMemoList = memoDao.getAll() // 최신 데이터 가져오기
            memoList.clear()
            memoList.addAll(updateMemoList)
            withContext(Dispatchers.Main){
                memoAdapter.notifyDataSetChanged()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    fun deleteAllData(){
        CoroutineScope(Dispatchers.IO).launch {
            memoDao.deleteAll()
            refreshAdapter()
        }
    }
}