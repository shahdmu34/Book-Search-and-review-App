package com.example.project4_cis436

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class recycleView(



     private var bookList: ArrayList<BookViewModel>,
     private var contextt: BookSearchFragment
 ):
    RecyclerView.Adapter<recycleView.BookViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ):recycleView.BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_inventory, parent, false)

        return recycleView.BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: recycleView.BookViewHolder, position: Int){
        val book = bookList.get(position)

        Picasso.get().load(book.thumbnail).into(holder.bookImg)
        holder.name.text = book.bookTitle
        holder.author.text = book.bookAuthor.toString()

        holder.itemView.setOnClickListener{

            val bookInfo = Intent(contextt, bookDescriptionFragment::class.java)

            bookInfo.putExtra("Title", book.bookTitle)
            bookInfo.putExtra("Author", book.bookAuthor)
            bookInfo.putExtra("Description", book.description)
            bookInfo.putExtra("PageNumber", book.BookPageNum)
            bookInfo.putExtra("pubDate", book.BookPubDate)
            bookInfo.putExtra("thumbnail", book.thumbnail)

            contextt.startActivity(bookInfo)
        }

    }

    override fun getItemCount(): Int {

        return bookList.size
    }


    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bookImg: ImageView = itemView.findViewById(R.id.bookPic)
        val name: TextView = itemView.findViewById(R.id.bookTitletv)
        val author: TextView = itemView.findViewById(R.id.authorTv)

    }

}