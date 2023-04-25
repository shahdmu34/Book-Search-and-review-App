package com.example.project4_cis436

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project4_cis436.databinding.FragmentBookSearchBinding
import com.example.project4_cis436.databinding.FragmentReviewBinding
import java.io.FileOutputStream
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.io.FileNotFoundException


class ReviewFragment : Fragment() {
    private lateinit var viewModel: ReviewViewModel
    private lateinit var reviewTextView: TextView
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review, container, false)
        reviewTextView = view.findViewById(R.id.reviewText)
        saveButton = view.findViewById(R.id.savBtn)
        viewModel = ViewModelProvider(requireActivity()).get(ReviewViewModel::class.java)

        viewModel.getUserInput().observe(viewLifecycleOwner, Observer { userInput ->
            reviewTextView.text = userInput
        })

        saveButton.setOnClickListener {
            val userInput = reviewTextView.text.toString()
            viewModel.setUserInput(userInput)
            Toast.makeText(context, "Review saved!", Toast.LENGTH_SHORT).show()
        }

        return view
    }



}



