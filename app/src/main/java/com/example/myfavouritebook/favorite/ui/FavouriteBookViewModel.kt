package com.example.myfavouritebook.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myfavouritebook.favorite.model.Book
import com.example.myfavouritebook.favorite.repository.BookRepository

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class FavouriteBookViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BookRepository) : ViewModel() {



    private var favListLiveData: LiveData<List<Book>>? = null

    fun getBookFavListLiveData(): LiveData<List<Book>> {
        return favListLiveData!!
    }


    fun insertBook(book: Book) = repository.insertBook(book)
    fun deleteBook(bookName: String) = repository.deleteBook(bookName)

    fun updateBook(book: Book) = repository.updateBook(book)

    fun getBooksFavList() {
        favListLiveData = repository.getFavBooks()
    }

}

