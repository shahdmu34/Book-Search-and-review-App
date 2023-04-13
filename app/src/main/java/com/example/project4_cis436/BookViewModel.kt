package com.example.project4_cis436

import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

class BookViewModel : ViewModel ( ){

    lateinit var bookTitle: String
    lateinit var bookAuthor: ArrayList<String>
    lateinit var description: String
    var BookPageNum: Int = 0
    lateinit var BookPubDate: String
    lateinit var thumbnail: String
}