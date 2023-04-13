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
import androidx.recyclerview.widget.GridLayoutManager
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
    private lateinit var bookList: ArrayList<BookViewModel>
    private lateinit var search: EditText
    private lateinit var searchBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookSearchBinding.inflate(inflater,container,false)
        search = binding.searchTv
        searchBtn = binding.searchBtn

        searchBtn.setOnClickListener { 
            if(search.text.toString().isNullOrEmpty()){
                search.setError("Please enter Search Correctly")
            }
            
            getBookSearch(search.getText().toString())
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(this).get(BookViewModel::class.java)        //accessing the view model
    }


     fun getBookSearch(searchResult: String) {

        bookList = ArrayList()
        val urlb = "https://www.googleapis.com/books/v1/volumes?q=$searchResult"
        val queue = Volley.newRequestQueue(activity?.applicationContext)


            val stringRequest = StringRequest(
                Method.GET, urlb,
                Response.Listener<String>{ response ->
                   // val urlb = response.getString("url")
                    val bookArray: JSONArray = JSONArray(response)

                    for(i in 0 until bookArray.length()) {
                        var bk: JSONObject = bookArray.getJSONObject(i)

                        val volume: JSONObject = bk.getJSONObject("volumeInfo")

                        val bkTitle = volume.optString("title")
                        val bkAuthors = volume.getJSONArray("authors")
                        val bkdescription = volume.optString("description")
                        val pageCount = volume.optInt("pageCount")
                        val pubDay = volume.optString("publishedDate")
                        val bkImage = volume.optJSONObject("imageLinks")
                        val bkpic = bkImage.optString("thumbnail")

                        val bkAuthorsList: ArrayList<String> = ArrayList()
                        if (bkAuthors.length() != 0) {
                            for (j in 0 until bkAuthors.length()) {
                                bkAuthorsList.add(bkAuthors.optString(i))
                            }
                        }

                        val bookData = bookRecycleVIew(
                            bkTitle,
                            bkAuthorsList,
                            bkdescription,
                            pageCount,
                            pubDay,
                            bkpic
                        )



                        bookList.add(viewModel)
                        val adapter = recycleView(bookList, this)

                        val layoutManager = GridLayoutManager(this, 3)
                        //val rView = binding.Rvbooks
                        val rview = binding.Rvbooks as RecyclerView

                        rView.layoutManager = layoutManager
                        rView.adapter = adapter



                        }







                },
                Response.ErrorListener {
                    Log.i("search"," THAT DIDN'T WORK!!!!!!!!!!!!!!!")
                }
            )
        queue.add(stringRequest)

    }


}

