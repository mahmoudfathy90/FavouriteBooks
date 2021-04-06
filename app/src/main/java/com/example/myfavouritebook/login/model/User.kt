package com.example.myfavouritebook.login.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var userName:String="",
    var passWord:String="",
    @Ignore
    var confirmPassword:String="",
)
