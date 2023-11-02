package com.example.fizzbuzz.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_score")
data class HistoryScore(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: Int,
    val userID: String,
    val date: String,
)