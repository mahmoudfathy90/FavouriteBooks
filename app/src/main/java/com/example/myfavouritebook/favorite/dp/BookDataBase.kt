package com.example.myfavouritebook.favorite.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfavouritebook.favorite.model.Book
import com.example.myfavouritebook.login.model.User
import com.example.myfavouritebook.register.dp.UserDao


@Database(entities = [Book::class,User::class],version = 2,exportSchema = false)
 abstract class BookDataBase :RoomDatabase() {

     abstract fun  bookDao() : BookDao
     abstract fun  userDao() : UserDao
}