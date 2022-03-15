package com.example.ekram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ekram.R
import com.example.ekram.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        //this code is to navigate user to pages when he click on them
        binding.addButton.setOnClickListener {
            //navigate to add post page + should i make it popup page like a dialog ?!
            findNavController().navigate(R.id.action_profileFragment_to_uploadContentFragment2)
        }
        binding.homePage.setOnClickListener {
            //navigate to home page
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
        binding.profile.setOnClickListener {
            //navigate to user profile page
            findNavController().navigate(R.id.profileFragment)
        }
        return binding.root
    }


}