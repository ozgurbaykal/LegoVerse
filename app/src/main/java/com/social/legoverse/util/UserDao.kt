package com.social.legoverse.util

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<Users>

    @Query("SELECT * FROM users WHERE user_name_or_mail = :userName AND password = :password LIMIT 1")
    fun getSpecificUser(userName: String?, password: String?): Boolean

    @Insert
    fun insert(folder: Users)

    @Delete
    fun delete(folder: Users)

}