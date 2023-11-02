package com.example.fizzbuzz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fizzbuzz.data.dao.HistoryScoreDao
import com.example.fizzbuzz.domain.model.HistoryScore

@Database(
    entities = [HistoryScore::class],
    version = 1,
)
abstract class LocalHistoryDb : RoomDatabase() {
    abstract val historyScoreDao: HistoryScoreDao
}