package com.example.myfavouritebook.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.myfavouritebook.databinding.ActivityLoginBinding
import com.example.myfavouritebook.favorite.ui.FavouriteActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login()
    }


    private fun login(){
        binding.login.setOnClickListener {
            var userName=binding.etUsername.text
            var password=binding.etPassword.text
            hideKeyboard()

            Intent(this, FavouriteActivity::class.java).apply {
                startActivity(this)
            }


        }
    }


     fun hideKeyboard() {
        val view: View? = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}