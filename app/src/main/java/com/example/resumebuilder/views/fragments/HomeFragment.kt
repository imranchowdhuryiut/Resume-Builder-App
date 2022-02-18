package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.resumebuilder.R
import com.example.resumebuilder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.btnBack.visibility = View.GONE
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.dashboard)
            btnAdd.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToEditDetailsFragment()
                )
            }
        }
    }

}