package com.example.myfavouritebook.register.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myfavouritebook.R
import com.example.myfavouritebook.databinding.ActivityRegisterBinding
import com.example.myfavouritebook.favorite.ui.FavouriteActivity
import com.example.myfavouritebook.favorite.ui.FavouriteBookViewModel
import com.example.myfavouritebook.login.model.User
import com.example.myfavouritebook.utils.hideKeyboard
import com.example.myfavouritebook.utils.validate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val userViewModel by viewModels<UserViewModel>()


    lateinit var binding: ActivityRegisterBinding
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user= User()
        binding.user=user
        register()
        insertSuccess()
    }


    private fun register() {
        binding.register.setOnClickListener {
            it.hideKeyboard(this)
            if (validate()){
                userViewModel.insertUser(user)
            }
        }
    }

    fun insertSuccess(){
        userViewModel.insertLiveData.observe(this){
            Toast.makeText(this, getString(R.string.insert_success), Toast.LENGTH_SHORT).show()
            Intent(this, FavouriteActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun validate():Boolean{
        if ( !user.userName.validate(this,getString(R.string.please_enter_user_name)))  return false
        if (!user.passWord.validate(this,getString(R.string.please_enter_password)))  return false
         if (!user.confirmPassword.validate(this,getString(R.string.please_enter_confirm_password))) return false
        return if (user.passWord != user.confirmPassword){
            Toast.makeText(this, getString(R.string.please_confirm_password), Toast.LENGTH_SHORT).show()
            false
        } else true

    }


}