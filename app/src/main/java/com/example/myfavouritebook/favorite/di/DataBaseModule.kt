package com.example.myfavouritebook.favorite.di

import android.app.Application
import androidx.room.Room
import com.example.myfavouritebook.favorite.dp.BookDao
import com.example.myfavouritebook.favorite.dp.BookDataBase

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
        return Room.databaseBuilder(application,BookDataBase::class.java,"pokemon_dp")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Singleton
    @Provides
    fun provideDao(bookDataBase: BookDataBase):BookDao =bookDataBase.bookDao()


}