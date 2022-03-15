package com.example.ekram.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ekram.database.PictureDataClass
import com.example.ekram.repository.FirebaseRepo

private const val TAG = "deleteViewModel"
class DeleteViewModel:ViewModel() {

    private val donationRepository = FirebaseRepo()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    fun deleteAnImage(bookOfCovidDataClassPhotos: PictureDataClass) {
        var imageSnapShot =
            donationRepository.deletePicturesFireStore(bookOfCovidDataClassPhotos).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        donationRepository.picturesCollection.document(document.id).delete()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
    }


}