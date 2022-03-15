package com.example.ekram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ekram.R
import com.example.ekram.database.PictureDataClass
import com.example.ekram.databinding.FragmentHomeBinding
import com.example.ekram.databinding.FragmentUploadContentBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        homeViewModel.postsLiveData().observe(
//            viewLifecycleOwner
//        ) {
//            updateUI(it)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //this code is to navigate user to pages when he click on them
        binding.addButton.setOnClickListener {
            //navigate to add post page + should i make it popup page like a dialog ?!
            findNavController().navigate(R.id.action_homeFragment_to_uploadContentFragment2)
        }
        binding.homePage.setOnClickListener {
            //navigate to home page
            findNavController().navigate(R.id.homeFragment)
        }
        binding.profile.setOnClickListener {
            //navigate to user profile page
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        return binding.root
    }

    private inner class Holder(binding: FragmentUploadContentBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: PictureDataClass

        fun bind(post: PictureDataClass) {
            this.post = post
        }
    }

    private fun updateUI(posts: List<PictureDataClass>) {
        val adapter = Adapter(posts)
        binding.recyclerView.adapter = adapter
    }

    private inner class Adapter(val posts: List<PictureDataClass>) : RecyclerView.Adapter<Holder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = FragmentUploadContentBinding.inflate(
                layoutInflater,
                parent,
                false
            )
            return Holder(binding)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val post = posts[position]
            holder.bind(post)
        }

        override fun getItemCount(): Int = posts.size
    }


}