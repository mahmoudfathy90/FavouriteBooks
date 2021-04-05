package com.example.myfavouritebook.favorite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var bookName: String?=null,
    var bookAuthorName: String?=null,
    var bookDesc: String?=null,
    var bookImageUrl: String?=null,
    var isReleased:Boolean=false
)

