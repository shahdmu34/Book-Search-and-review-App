package com.example.project4_cis436

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.project4_cis436.databinding.FragmentBookSearchBinding
import org.json.JSONArray
import org.json.JSONObject


class BookSearchFragment : Fragment() {

    private lateinit var binding: FragmentBookSearchBinding
    private lateinit var viewModel: BookViewModel
    private lateinit var bookList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookSearchBinding.inflate(inflater, container, false)

        binding.searchBtn.setOnClickListener { searchUpdate() }

        bookList = binding.bookRv
        bookList.layoutManager = LinearLayoutManager(activity)
        bookList.adapter = BookAdapter(emptyList()) // pass an empty list to start with


        val reviewButton = binding.reviewBtn

       reviewButton.setOnClickListener {
            findNavController().navigate(R.id.action_bookSearchFragment_to_reviewFragment)
        }
        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(this).get(BookViewModel::class.java)        //accessing the view model
    }


    private fun searchUpdate() {                   //function takes in the input and searches the api to find a match

        val searchText = binding.searchTv.text.toString()        //fetching the input

        val bookUrl = "https://www.googleapis.com/books/v1/volumes?q=$searchText"

        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val stringRequest = StringRequest(
            Method.GET, bookUrl,
            Response.Listener<String>{ response ->
                val bookObject : JSONObject = JSONObject(response)
                val bookArray : JSONArray = bookObject.getJSONArray("items")

                val books = mutableListOf<Book>()

                for (i in 0 until bookArray.length()) {

                    val volume = bookArray.getJSONObject(i).getJSONObject("volumeInfo")
                    val title = volume.getString("title")
                    val author = volume.getJSONArray("authors").getString(0)
                    val description = volume.getString("description")
                    val imageurl = volume.getJSONObject("imageLinks")

                    val bookPic  = imageurl.getString("thumbnail")
                    Log.i("image display", "thumbnail: ${bookPic}")
                    books.add(Book(title, author, bookPic,description ))
                }

                val adapter = bookList.adapter as BookAdapter
                adapter.books = books
                adapter.notifyDataSetChanged()
            },Response.ErrorListener { Log.i("searchFragment", "THAT DIDN'T WORK!!") }

        )
        queue.add(stringRequest)

    }

}
