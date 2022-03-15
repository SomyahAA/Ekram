package com.example.ekram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ekram.R
import com.example.ekram.databinding.FragmentUploadContentBinding

open class UploadContentFragment : Fragment() {

    private lateinit var binding: FragmentUploadContentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentUploadContentBinding.inflate(layoutInflater)
        //this code is to navigate user to pages when he click on them
        binding.addButton.setOnClickListener {
            //navigate to add post page + should i make it popup page like a dialog ?!
            findNavController().navigate(R.id.uploadContentFragment)
        }
        binding.homePage.setOnClickListener {
            //navigate to home page
            findNavController().navigate(R.id.action_uploadContentFragment_to_homeFragment2)
        }
        binding.profile.setOnClickListener {
            //navigate to user profile page
            findNavController().navigate(R.id.action_uploadContentFragment_to_profileFragment2)
        }
        return binding.root
    }

}