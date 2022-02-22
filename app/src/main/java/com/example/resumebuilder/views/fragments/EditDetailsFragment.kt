package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.resumebuilder.R
import com.example.resumebuilder.databinding.FragmentEditDetailsBinding
import com.example.resumebuilder.utils.Resource
import com.example.resumebuilder.viewModels.ResumeViewModel

class EditDetailsFragment : Fragment() {

    private var _binding: FragmentEditDetailsBinding? = null

    private val args by navArgs<EditDetailsFragmentArgs>()

    private val mViewModel by viewModels<ResumeViewModel>()

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
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToPersonalDetailsFragment(
                        args.resumeId
                    )
                )
            }
            btnObjective.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditObjectiveFragment(
                        args.resumeId
                    )
                )
            }
            btnExperience.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditExperienceFragment(
                        args.resumeId
                    )
                )
            }
            btnSkill.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditSkillsFragment(
                        args.resumeId
                    )
                )
            }
            btnEducationalDetails.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditEducationFragment(
                        args.resumeId
                    )
                )
            }
            btnProjects.setOnClickListener {
                findNavController().navigate(
                    EditDetailsFragmentDirections.actionEditDetailsFragmentToEditProjectFragment(
                        args.resumeId
                    )
                )
            }
            btnGeneratePdf.setOnClickListener {
                getResume()
            }
        }
    }

    private fun getResume() {
        mViewModel.getResume(args.resumeId).observe(viewLifecycleOwner, { resources ->
            resources?.let {
                when (resources.status) {
                    Resource.Status.SUCCESS -> {
                        _binding?.progressBar?.visibility = View.GONE
                        val data = resources.data
                        data?.let {
                            findNavController().navigate(
                                EditDetailsFragmentDirections.actionEditDetailsFragmentToWritePdfActivity(
                                    it
                                )
                            )
                        }
                    }
                    Resource.Status.ERROR -> {
                        _binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), resources.msg, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING -> {
                        _binding?.progressBar?.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}