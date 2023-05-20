package com.example.umc_week8_2

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [FavData::class], version = 1, exportSchema = false)
abstract class FavDatabase : RoomDatabase() {
    abstract fun favDataDao(): FavDataDAO
}