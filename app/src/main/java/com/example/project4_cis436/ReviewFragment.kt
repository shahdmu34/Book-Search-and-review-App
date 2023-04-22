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
import android.util.Log
import android.widget.Toast
import java.io.File
import java.io.FileNotFoundException


class ReviewFragment : Fragment() {
    private lateinit var binding : FragmentReviewBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReviewBinding.inflate(inflater, container, false)

        val saveButton = binding.savBtn


        saveButton.setOnClickListener {
            var input = binding.reviewText.text.toString()


            Log.i("review fragment", "review: ${input}")

            //val file = "saveFile.txt"
            val file = File(requireContext().filesDir, "saveFile.txt")         //set the file to the txt
            if (!file.exists()) {                                                 //checks if its in the project files
                file.createNewFile()
            }

            try {
                val fileOutputStream = requireContext().openFileOutput(file.toString(), Context.MODE_PRIVATE)
                fileOutputStream.write(input.toByteArray())                   //writes to the file
                fileOutputStream.close()

                Log.i("review fragment", "Review saved to file.")

            }catch(e : FileNotFoundException){
                e.printStackTrace()
            }catch(e: Exception){
                e.printStackTrace()
            }




        }


        return binding.root
    }


}

