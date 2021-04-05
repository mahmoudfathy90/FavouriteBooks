package com.example.myfavouritebook.favorite.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfavouritebook.favorite.model.Book


@Database(entities = [Book::class],version = 1,exportSchema = false)
 abstract class BookDataBase :RoomDatabase() {

     abstract fun  bookDao() : BookDao
}