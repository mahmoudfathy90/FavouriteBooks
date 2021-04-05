package com.example.myfavouritebook.favorite.repository

import androidx.lifecycle.LiveData
import com.example.myfavouritebook.favorite.dp.BookDao
import com.example.myfavouritebook.favorite.model.Book

import javax.inject.Inject

class BookRepository @Inject constructor(
    var bookDao: BookDao
)
{
    fun insertBook(book: Book) = bookDao.insertBook(book)
    fun deleteBook(bookName: String) = bookDao.deleteBook(bookName = bookName)
    fun updateBook(book: Book) = bookDao.updateBook(book)
    fun getFavBooks(): LiveData<List<Book>> = bookDao.getFavBooks()
}
