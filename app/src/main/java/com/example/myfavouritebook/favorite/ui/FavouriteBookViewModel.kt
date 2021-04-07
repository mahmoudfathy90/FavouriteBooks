package com.example.myfavouritebook.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfavouritebook.favorite.model.Book
import com.example.myfavouritebook.favorite.repository.BookRepository
import com.example.myfavouritebook.utils.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class FavouriteBookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {


    private var favListStateFlow = MutableStateFlow<Resource<List<Book>>>(Resource.loading(null))

    fun getBooks(): StateFlow<Resource<List<Book>>> {
        return favListStateFlow
    }


    fun insertBook(book: Book) = repository.insertBook(book)
    fun deleteBook(bookName: String) = repository.deleteBook(bookName)

    fun updateBook(book: Book) = repository.updateBook(book)

    fun getBooksFavList() {
        viewModelScope.launch {
            delay(1000)
            repository.getFavBooks()
                .catch { e ->
                    favListStateFlow.value = Resource.error(e.message)
                }
                .collect {
                    favListStateFlow.value = Resource.success(it)
                }
        }
    }

}

