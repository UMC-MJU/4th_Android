package com.example.umc_week8_2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// 따로 메서드를 구현하지 않아도 Room 라이브러리가 자동으로 구현해준다.
@Dao
interface RoomMemoDAO {

    @Query("select * from room_memo") // 쿼리: 데이터베이스에서 요청하는 명령문, room_memo 테이블을 불러오는 것
    suspend fun getAll() : List<RoomMemo>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복된 데이터가 충돌되면(중복되면) 업데이트를 해준다
    suspend fun insert(memo:RoomMemo)

    @Update
    suspend fun update(memo: RoomMemo)

    @Delete
    suspend fun delete(memo:RoomMemo)
}