package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.R
import com.example.resumebuilder.data.model.WorkExperience
import com.example.resumebuilder.databinding.FragmentEditExperienceBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.viewModels.WorkExperienceViewModel
import com.example.resumebuilder.views.adapters.WorkExperienceAdapter
import com.google.android.material.snackbar.Snackbar

class EditExperienceFragment : Fragment(), OnItemClickCallback<WorkExperience> {

    private var _binding: FragmentEditExperienceBinding? = null

    private val mAdapter: WorkExperienceAdapter = WorkExperienceAdapter(this)

    private val args by navArgs<EditExperienceFragmentArgs>()

    private val mViewModel by viewModels<WorkExperienceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditExperienceBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getSavedExperiences()
    }

    private fun getSavedExperiences() {
        mViewModel.getExperienceByResumeId(args.resumeId)
            .observe(viewLifecycleOwner, {data->
                mAdapter.submitList(data)
            })
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.work_summary)
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnAddExperience.setOnClickListener {
                showExperienceAddView()
            }
            btnSaveExperience.setOnClickListener {
                if(validateView(it)) {
                    saveExperience()
                    hideExperienceAddView()
                }
            }
            rvWorkExperience.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvWorkExperience.adapter = mAdapter
        }
        hideExperienceAddView()
    }

    private fun hideExperienceAddView() {
        _binding?.llExperienceView?.visibility = View.GONE
        _binding?.btnSaveExperience?.visibility = View.GONE
        _binding?.btnAddExperience?.visibility = View.VISIBLE
    }

    private fun showExperienceAddView() {
        _binding?.llExperienceView?.visibility = View.VISIBLE
        _binding?.btnSaveExperience?.visibility = View.VISIBLE
        _binding?.btnAddExperience?.visibility = View.GONE
    }

    private fun saveExperience() {
        val companyName = _binding?.etCompanyName?.text.toString()
        val duration = _binding?.etDuration?.text.toString().toInt()
        clearEditView()
        val model = WorkExperience(
            companyName = companyName,
            duration = duration,
            resumeId = args.resumeId
        )
        mViewModel.saveWorkExperience(model)
    }

    private fun clearEditView() {
        _binding?.etCompanyName?.text?.clear()
        _binding?.etDuration?.text?.clear()
    }

    private fun validateView(view: View): Boolean {
        if (_binding?.etCompanyName?.text?.isEmpty() == true) {
            Snackbar.make(view, "Company name must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etDuration?.text?.isEmpty() == true) {
            Snackbar.make(view, "Duration must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(model: WorkExperience) {
        mViewModel.deleteWorkExperience(model)
    }

}