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
    //private lateinit var searchBn : Button
    //private lateinit var searchText :  EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookSearchBinding.inflate(inflater,container,false)


        binding.searchBtn.setOnClickListener { searchUpdate() }
        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel= ViewModelProvider(this).get(BookViewModel::class.java)        //accessing the view model
    }


    private fun searchUpdate() {                   //function takes in the input and searches the api to find a match

        val searchText = binding.searchTv.text.toString()        //fetching the input
        var txtView = binding.textView.toString()

        val bookUrl = "https://www.googleapis.com/books/v1/volumes?q=$searchText"

        val queue = Volley.newRequestQueue(activity?.applicationContext)

        val stringRequest = StringRequest(
            Method.GET, bookUrl,
            Response.Listener<String>{ response ->
                val bookArray : JSONArray = JSONArray(response)

                for(i in 0 until bookArray.length()){
                    var theBook : JSONObject = bookArray.getJSONObject(i)
                    val volumes : JSONObject = theBook.getJSONObject("volumeInfo")

                    txtView = volumes.optString("title")
                }
            },Response.ErrorListener { Log.i("serchFragment", "THAT DIDN'T WORK!!") }

        )
        queue.add(stringRequest)

    }

}

