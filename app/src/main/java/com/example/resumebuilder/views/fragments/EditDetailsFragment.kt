package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.resumebuilder.R
import com.example.resumebuilder.databinding.FragmentEditDetailsBinding

class EditDetailsFragment : Fragment() {

    private var _binding: FragmentEditDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.edit_details)
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnPersonalDetail.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToPersonalDetailsFragment()
                )
            }
            btnObjective.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditObjectiveFragment()
                )
            }
            btnExperience.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditExperienceFragment()
                )
            }
            btnSkill.setOnClickListener {

            }
            btnEducationalDetails.setOnClickListener {

            }
            btnProjects.setOnClickListener {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}