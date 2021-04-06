package com.example.myfavouritebook.register.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfavouritebook.favorite.model.Book

import com.example.myfavouritebook.login.model.User
import com.example.myfavouritebook.register.repository.UserRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable

import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {



    private var userLiveData: LiveData<List<User>>? = null

    fun getUserLiveData(): LiveData<List<User>> {
        return userLiveData!!
    }
    val insertLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


    fun insertUser(user: User) {
        Observable.fromCallable{
            repository.insertUser(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                insertLiveData.postValue(true)
            }
    }

    fun getUser(user: User) {
        userLiveData = repository.getUser(user)
    }
}

