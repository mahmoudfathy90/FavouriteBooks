package com.example.myfavouritebook.utils

import android.content.Context
import android.widget.Toast


    fun String.validate(context: Context, validateText: String): Boolean {
        return if (this.isNullOrEmpty()) {
            Toast.makeText(context, validateText, Toast.LENGTH_SHORT).show()
            false
        } else true
    }
