package com.example.umc_week8_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// 사용할 엔티티 선언, version:엔티티 구조 변경 시 구분해주는 역할, exportSchema : 스키마 내보내기 설정
@Database(entities = [RoomMemo::class], version = 2, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDAO

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'room_memo' ADD COLUMN 'star' INTEGER DEFAULT 0 NULL")
                database.execSQL("ALTER TABLE 'room_memo' ADD COLUMN 'isFav' INTEGER DEFAULT 0 NULL")
            }
        }
    }
}