package com.example.myfavouritebook.favorite.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myfavouritebook.favorite.model.Book


@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Query("delete from book_table where bookName =:bookName")
    fun deleteBook(bookName: String)


    @Update(entity = Book::class)
    fun updateBook(book: Book)


    @Query("select* from book_table")
    fun getFavBooks(): LiveData<List<Book>>

}