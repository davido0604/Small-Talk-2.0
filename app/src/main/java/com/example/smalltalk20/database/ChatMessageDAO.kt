package com.example.smalltalk20.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface ChatMessageDAO {
    @Insert
    fun addMessage(chatMessage: ChatMessage)

    @Delete
    fun removeMessage(chatMessage: ChatMessage)
}