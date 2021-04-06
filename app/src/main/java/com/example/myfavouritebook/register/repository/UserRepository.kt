package com.example.myfavouritebook.register.repository


import androidx.lifecycle.LiveData
import com.example.myfavouritebook.login.model.User
import com.example.myfavouritebook.register.dp.UserDao

import javax.inject.Inject

class UserRepository @Inject constructor(
    var userDao: UserDao
)
{
    fun insertUser(user: User) = userDao.insertUser(user)

    fun getUser(user: User):LiveData<List<User>> = userDao.getUser(user.userName,user.passWord)
}
