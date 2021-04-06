package com.example.myfavouritebook.favorite.di

import android.app.Application
import androidx.room.Room
import com.example.myfavouritebook.favorite.dp.BookDao
import com.example.myfavouritebook.favorite.dp.BookDataBase
import com.example.myfavouritebook.register.dp.UserDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {


    @Provides
    @Singleton
    fun getRoomDataBase(application: Application):BookDataBase{
        return Room.databaseBuilder(application,BookDataBase::class.java,"book_dp")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Singleton
    @Provides
    fun provideBookDao(bookDataBase: BookDataBase):BookDao =bookDataBase.bookDao()


    @Singleton
    @Provides
    fun provideUSerDao(bookDataBase: BookDataBase):UserDao =bookDataBase.userDao()


}