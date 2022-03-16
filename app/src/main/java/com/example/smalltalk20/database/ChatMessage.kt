package com.example.smalltalk20.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ChatMessage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val message: String,
    val username: String,
    val date: Long,
)