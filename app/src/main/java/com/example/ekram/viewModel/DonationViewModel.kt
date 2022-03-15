package com.example.ekram.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ekram.database.PictureDataClass
import com.example.ekram.repository.FirebaseRepo
import com.google.firebase.auth.FirebaseAuth

class DonationViewModel : ViewModel() {

    private val donationRepository = FirebaseRepo()

    private val userLiveDataError = MutableLiveData<String>()
    private val userLiveDataSuccessful = MutableLiveData<String>()

    private fun uploadPictureStorage(
        uri: Uri,
        name: String,
        title: String,
        description: String,
        donationCategory: String,
        location: String
    ) {
        val reference = uri.lastPathSegment?.let {
            donationRepository.uploadPicturesStorage(
                name,
                title,
                description,
                donationCategory,
                location
            )
        }
        reference?.putFile(uri)?.addOnSuccessListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener {
            userLiveDataError.postValue("Failed to upload file")


        }
    }

    fun uploadPictureFireStore(
        uri: Uri,
        imageName: String,
        title: String,
        description: String,
        donationCategory: String,
        location: String
    ) {
        uri.lastPathSegment?.let {
            donationRepository.uploadPicturesFireStore()
                .add(
                    PictureDataClass(
                        imageName,
                        FirebaseAuth.getInstance().uid.toString(),
                        title,
                        description,
                        donationCategory,
                        location
                    )
                )
        }
            ?.addOnSuccessListener {
                uploadPictureStorage(uri, imageName, title, description, donationCategory, location)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
                userLiveDataError.postValue("Failed to upload file")

            }
    }


}