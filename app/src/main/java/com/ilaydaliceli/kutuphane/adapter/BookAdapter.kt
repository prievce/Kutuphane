package com.ilaydaliceli.kutuphane.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ilaydaliceli.kutuphane.R
import com.ilaydaliceli.kutuphane.databinding.ItemBookBinding
import com.ilaydaliceli.kutuphane.model.Book
import downloadURL


class BookAdapter (var bookList : ArrayList<Book>, private var onClick:(position:Int)->Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    class BookViewHolder (var view : ItemBookBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemBookBinding>(inflater, R.layout.item_book,parent, false )

        return BookViewHolder(view)

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.bookTV.text = bookList[position].title
        holder.view.bookTV.text = bookList[position].authors
        holder.view.cvItem.setOnClickListener {
            onClick(position)
        }

        //Glide.with(holder.view.root).load(countryList[position].flagUrl).into(holder.view.countryIV)
        holder.view.bookIV.downloadURL(bookList[position].bookUrl)


    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList : List<Book>){
        bookList = newList as ArrayList<Book>
        notifyDataSetChanged()
    }



}