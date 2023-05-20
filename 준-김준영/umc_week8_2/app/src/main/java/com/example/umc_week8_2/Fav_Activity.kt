package com.example.umc_week8_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.umc_week8_2.databinding.FavoriteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fav_Activity : AppCompatActivity() {
    private lateinit var binding: FavoriteBinding
    lateinit var favDatabase:FavDatabase
    val favList = mutableListOf<FavData>()
    lateinit var favDao:FavDataDAO
    lateinit var favAdapter:Fav_RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favDatabase = Room.databaseBuilder(this, FavDatabase::class.java, "room_db_fav") // 데이터베이스 생성
            //.allowMainThreadQueries()  공부할때만 쓴다 -> 원래 Room은 메인스레드에서 사용불가
            .fallbackToDestructiveMigration()
            .build()

        favDao = favDatabase.favDataDao()
        favAdapter = Fav_RecyclerAdapter(favList, favDao)

        refreshAdapter()

        binding.rvFav.adapter = favAdapter
        binding.rvFav.layoutManager = LinearLayoutManager(this)
    }

    fun insertMemo(data:FavData){
        CoroutineScope(Dispatchers.IO).launch {
            favDao.insert(data)
            refreshAdapter()
        }
    }

    fun refreshAdapter(){ // 메모리스트를 갱신하는 부분
        CoroutineScope(Dispatchers.IO).launch {
            val updateMemoList = favDao.getAll() // 최신 데이터 가져오기
            favList.clear()
            favList.addAll(updateMemoList)
            withContext(Dispatchers.Main){
                favAdapter.notifyDataSetChanged()
            }
        }
    }

}