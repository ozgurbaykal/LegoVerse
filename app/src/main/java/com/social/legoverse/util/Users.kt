package com.social.legoverse.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["user_name_or_mail"], unique = true)])
class Users (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "user_name_or_mail") val userName: String?,
    @ColumnInfo(name = "password") val password: String?,
)