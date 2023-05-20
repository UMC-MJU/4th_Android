package com.example.umc_week8_2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_memo")
class FavData {
    @PrimaryKey(autoGenerate = true) // no 에 값이 없을 때 자동증가된 숫자값을 db에 입력해준다
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date") // 코드에서는 datatime이라 쓰고 데이터에선 date로 읽는다
    var datetime: Long = 0

    @ColumnInfo
    var star: Int? = 0

    @ColumnInfo
    var isFav: Int = 0

    fun setIsFav(value: Int) {
        isFav = value
    }

    fun setIsNo(value: Long) {
        no = value
    }


    constructor(content: String, datetime: Long, star: Int, isFav: Int) { // 생성자를 만들어 놓으면 코드가 좀 더 짧아진다 (ex val memo memo.content="" memo.datetime="" --> val memo = RoomMemo("", "")
        this.content = content
        this.datetime = datetime
        this.star = star
        this.isFav = isFav
    }
}