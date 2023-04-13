package com.example.project4_cis436

import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.project4_cis436.databinding.FragmentBookDescriptionBinding
import com.squareup.picasso.Picasso

class bookDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentBookDescriptionBinding
    private lateinit var title: TextView
    private lateinit var author: TextView
    private lateinit var bookImg: ImageView
    private lateinit var description: TextView
    private lateinit var pageNum: TextView
    private lateinit var date: TextView
    private lateinit var review: Button       //need to be done so it leads to the new page to write a review

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bookName = requireActivity().intent.getStringExtra("title")
        val bookAuthor = requireActivity().intent.getStringExtra("author")
        val pic = requireActivity().intent.getStringExtra("thumbnail")
        val bDescription = requireActivity().intent.getStringExtra("description")
        val pageNumb = requireActivity().intent.getIntExtra("pageCount", 0)
        val publishedDate = requireActivity().intent.getStringExtra("publishedDate")


        title.setText(bookName)
        author.setText(bookAuthor)
        Picasso.get().load(pic).into(bookImg)
        description.setText(bDescription)
        pageNum.setText( "${pageNumb} pages")
        date.setText("First published " +  publishedDate)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentBookDescriptionBinding.inflate(inflater, container, false)
        title = binding.titleTv
        author = binding.authorTV
        bookImg = binding.bookImag
        description = binding.descriptionTV
        pageNum = binding.pagNumTv
        date = binding.pubDateTv
        review = binding.ReviewBTn





        return binding.root
    }

}