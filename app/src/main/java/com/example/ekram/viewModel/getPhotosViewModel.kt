package com.example.ekram.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ekram.database.PictureDataClass
import com.example.ekram.repository.FirebaseRepo
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "getPhotosViewModel"
class GetPhotosViewModel:ViewModel() {

    private val donationRepository = FirebaseRepo()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    private val uriLiveDataForPhotos = MutableLiveData<List<PictureDataClass>>()
    val uriLiveDataForPhotosDetail = MutableLiveData<PictureDataClass>()


    /**
     * The function below job is to get the photos from the firebase
     */
    fun getBookOfCovidPhotos(){
        val photoList = mutableListOf<PictureDataClass>()
        var imageSnapShot = donationRepository.getPhotosFormFireStore().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val x = document.toObject<PictureDataClass>()
                    x.uid
                    photoList.add(x)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                uriLiveDataForPhotos.postValue(photoList)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}