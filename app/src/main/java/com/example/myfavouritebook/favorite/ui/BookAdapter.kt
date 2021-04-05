package com.example.myfavouritebook.favorite.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfavouritebook.databinding.ItemFavouriteBinding
import com.example.myfavouritebook.favorite.model.Book

class BookAdapter(private val items: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val itemBinding =
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(itemBinding)
    }

    fun getBookAt(position: Int):Book = items[position]

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class BookViewHolder(private val binding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book) {
            binding.apply {
                Glide.with(binding.root.context).load(item.bookImageUrl).into(imgBook)
                tvBookName.text = item.bookName
                tvBookDesc.text = item.bookDesc
                tvBookAuthor.text = item.bookAuthorName

                if (item.isReleased){
                    tvIsReleased.visibility=View.VISIBLE
                }
                else{
                    tvIsReleased.visibility=View.GONE
                }
            }
        }
    }
}