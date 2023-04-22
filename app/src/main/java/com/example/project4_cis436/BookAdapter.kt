package com.example.project4_cis436

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookAdapter(var books: List<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.title_tv)
        val authorTv: TextView = itemView.findViewById(R.id.author_tv)
        val coverImageView: ImageView = itemView.findViewById(R.id.bookimgview)
        val descriptionTv: TextView = itemView.findViewById(R.id.descriptionTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.titleTv.text = book.title
        holder.authorTv.text = book.author
        holder.descriptionTv.text = book.description
        Picasso.get().load(book.bookPic).into(holder.coverImageView)

    }

    override fun getItemCount(): Int {
        return books.size
    }

}
