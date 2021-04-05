package com.example.myfavouritebook.addBook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myfavouritebook.databinding.ActivityAddBookBinding
import com.example.myfavouritebook.favorite.model.Book
import com.example.myfavouritebook.favorite.ui.FavouriteBookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookActivity : AppCompatActivity() {

    private val favouriteBookViewModel by viewModels<FavouriteBookViewModel>()
    lateinit var book:Book

    lateinit var binding: ActivityAddBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
        book= Book()
        binding.addLogin.setOnClickListener {
          book.bookName= binding.etBookName.text.toString()
          book.bookDesc= binding.etBookDesc.text.toString()
          book.bookAuthorName= binding.etBookAuthorName.text.toString()
          book.bookImageUrl= binding.etBookUrl.text.toString()
            if (binding.checkBox.isChecked){
                book.isReleased=true
            }
            favouriteBookViewModel.insertBook(book)
            Toast.makeText(this, "this Book add to    fav ", Toast.LENGTH_SHORT).show()

        }
    }
}