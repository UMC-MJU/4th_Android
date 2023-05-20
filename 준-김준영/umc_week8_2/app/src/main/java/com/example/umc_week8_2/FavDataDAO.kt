package com.example.umc_week8_2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavDataDAO {
    @Query("select * from fav_memo") // 쿼리: 데이터베이스에서 요청하는 명령문, room_memo 테이블을 불러오는 것
    suspend fun getAll() : List<FavData>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복된 데이터가 충돌되면(중복되면) 업데이트를 해준다
    suspend fun insert(favMemo:FavData)

    @Update
    suspend fun update(favMemo: FavData)

    @Delete
    suspend fun delete(favMemo: FavData)
}