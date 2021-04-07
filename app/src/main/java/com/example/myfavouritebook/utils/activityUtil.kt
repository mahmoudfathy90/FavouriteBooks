package com.example.myfavouritebook.utils

import android.app.Activity
import android.content.Intent

fun Activity.goToActivity(activity:Activity){
    Intent(this, activity::class.java).apply {
        startActivity(this)
    }
}