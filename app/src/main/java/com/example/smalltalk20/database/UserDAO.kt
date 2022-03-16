package com.example.smalltalk20.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert
    fun saveUser(user: User)

    @Delete
    fun removeUser(user: User)

    @Query("DELETE FROM user")
    fun removeAllUsers()

    @Query("SELECT * FROM user LIMIT 1")
    fun getLoggedInUser(): User
}