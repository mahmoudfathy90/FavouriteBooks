package com.example.myfavouritebook.register.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

import com.example.myfavouritebook.login.model.User


@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)


    @Query("select * from user_table  where userName =:userName and passWord=:password")
    fun getUser(userName: String,password:String): LiveData<List<User>>

}