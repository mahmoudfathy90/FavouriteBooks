package com.example.myfavouritebook.favorite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myfavouritebook.addBook.AddBookActivity

import com.example.myfavouritebook.databinding.ActivityFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {

    private val favouriteBookViewModel by viewModels<FavouriteBookViewModel>()

    lateinit var binding:ActivityFavouriteBinding
    lateinit var adapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        favouriteBookViewModel.getBooksFavList()
        setUpDeleteSwipe()
        getList()
        binding.addLogin.setOnClickListener {
            Intent(this,AddBookActivity::class.java).apply {
                startActivity(this)
            }
        }
    }



    private fun setUpDeleteSwipe(){
        var swipeCallBack: ItemTouchHelper.SimpleCallback= object  : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var book =adapter.getBookAt(viewHolder.adapterPosition)
                favouriteBookViewModel.deleteBook(book.bookName!!)
                adapter.notifyDataSetChanged()
                Toast.makeText(this@FavouriteActivity, "this Book deleted from  fav ", Toast.LENGTH_SHORT).show()
            }

        }
        var itemTouchHelper= ItemTouchHelper(swipeCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rcBooks)
    }


    private fun setUpUpdateSwipe(){
        var swipeCallBack: ItemTouchHelper.SimpleCallback= object  : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var book =adapter.getBookAt(viewHolder.adapterPosition)
                adapter.notifyDataSetChanged()
            }

        }
        var itemTouchHelper= ItemTouchHelper(swipeCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rcBooks)
    }


    private fun getList(){
        favouriteBookViewModel.getBookFavListLiveData()?.observe(this
        ) {

            if (it.isEmpty()){
                binding.tvEmptyText.visibility=View.VISIBLE
            }
            else{
                binding.tvEmptyText.visibility=View.GONE
                adapter = BookAdapter(it)
                binding.rcBooks.adapter = adapter
            }

        }
   }

}