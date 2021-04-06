package com.example.myfavouritebook.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myfavouritebook.R
import com.example.myfavouritebook.databinding.ActivityLoginBinding
import com.example.myfavouritebook.favorite.ui.FavouriteActivity
import com.example.myfavouritebook.login.model.User
import com.example.myfavouritebook.register.ui.RegisterActivity
import com.example.myfavouritebook.register.ui.UserViewModel
import com.example.myfavouritebook.utils.hideKeyboard
import com.example.myfavouritebook.utils.validate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val userViewModel by viewModels<UserViewModel>()
    lateinit var user: User
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = User()
        binding.user = user
        login()
        register()

    }

    private fun register() {
        binding.tvRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).apply {
                startActivity(this)
            }
        }

    }


    private fun validate(): Boolean {
        if (!user.userName.validate(this, getString(R.string.please_enter_user_name))) return false
        return user.passWord.validate(this, getString(R.string.please_enter_password))

    }

    private fun login() {
        binding.login.setOnClickListener {
            it.hideKeyboard(this)
            if (validate()) {
                userViewModel.getUser(user)
                observeForLogin()
            }
        }


    }

    private fun observeForLogin(){
        userViewModel.getUserLiveData().observe(this){
            if (it.isEmpty()) {
                Toast.makeText(this, getString(R.string.user_name_or_password_error), Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                Intent(this, FavouriteActivity::class.java).apply {
                    startActivity(this)
                }
            }

        }
    }
}