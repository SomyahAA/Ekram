package com.example.ekram.view

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.ekram.databinding.FragmentUploadContentBinding
import com.example.ekram.viewModel.DonationViewModel
import java.util.*


class UploadContentFragment : Fragment() {
    private val uploadContentViewModel: DonationViewModel by activityViewModels()

    private lateinit var sharedPref: SharedPreferences
    private var uri: Uri? = null

    private var picRequestCode: Int = 6
    private lateinit var binding: FragmentUploadContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUploadContentBinding.inflate(inflater, container, false)
        return binding.root
    }


    /**
     * The function below is to select an image from the user phone
     */
    private fun selectPic() {

        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, picRequestCode)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.saveTitleDecripButton.setOnClickListener {
            if (requestCode == picRequestCode && resultCode == Activity.RESULT_OK) {
                val title = binding.titleEditText.text.toString()
                val description = binding.decripEditText.text.toString()
                val location = binding.LocationEditText.text.toString()
                val donationType = binding.donationTypeEditText.text.toString()
                uri = data?.data!!
                var date = Date()

                uploadContentViewModel.uploadPictureFireStore(
                    uri!!,
                    Math.random().toString(),
                    title,
                    description,
                    donationType,
                    location
                )

            } else {
                Toast.makeText(context, "Nothing was selected", Toast.LENGTH_SHORT).show()
            }

        }

    }
}