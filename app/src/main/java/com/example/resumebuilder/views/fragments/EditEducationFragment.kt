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
import com.example.resumebuilder.data.model.Education
import com.example.resumebuilder.databinding.FragmentEditEducationBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.viewModels.EducationViewModel
import com.example.resumebuilder.views.adapters.EducationAdapter
import com.google.android.material.snackbar.Snackbar

class EditEducationFragment : Fragment(), OnItemClickCallback<Education> {

    private var _binding:FragmentEditEducationBinding? = null

    private val mAdapter: EducationAdapter = EducationAdapter(this)

    private val args by navArgs<EditEducationFragmentArgs>()

    private val mViewModel by viewModels<EducationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditEducationBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getSavedEducations()
    }

    private fun getSavedEducations() {
        mViewModel.getEducationByResumeId(args.resumeId)
            .observe(viewLifecycleOwner, {list->
                mAdapter.submitList(list)
            })
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.educational_details)
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnAddEducation.setOnClickListener {
                showEducationAddView()
            }
            btnSaveEducation.setOnClickListener {
                if (validateInput(it)) {
                    saveEducation()
                    hideEducationAddView()
                }
            }
            rvEducation.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvEducation.adapter = mAdapter
        }
        hideEducationAddView()
    }

    private fun saveEducation() {
        val degree = _binding?.etDegree?.text.toString()
        val passingYear = _binding?.etPassingYear?.text.toString().toInt()
        val grade = _binding?.etGrade?.text.toString().toDouble()
        clearEditView()
        val model = Education(
            resumeId = args.resumeId,
            grade = grade,
            degree = degree,
            passingYear = passingYear
        )
        mViewModel.saveEducation(model)
    }

    private fun clearEditView() {
        _binding?.etDegree?.text?.clear()
        _binding?.etPassingYear?.text?.clear()
        _binding?.etGrade?.text?.clear()
    }

    private fun showEducationAddView() {
        _binding?.llEducationView?.visibility = View.VISIBLE
        _binding?.btnSaveEducation?.visibility = View.VISIBLE
        _binding?.btnAddEducation?.visibility = View.GONE
    }

    private fun validateInput(it: View): Boolean {
        if (_binding?.etDegree?.text?.isEmpty() == true){
            Snackbar.make(it, "Degree name must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etGrade?.text?.isEmpty() == true) {
            Snackbar.make(it, "Grade must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etPassingYear?.text?.isEmpty() == true) {
            Snackbar.make(it, "Passing year must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etPassingYear?.text.toString().length < 4) {
            Snackbar.make(it, "Please input a valid year", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun hideEducationAddView() {
        _binding?.llEducationView?.visibility = View.GONE
        _binding?.btnSaveEducation?.visibility = View.GONE
        _binding?.btnAddEducation?.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(model: Education) {
        mViewModel.deleteEducation(model)
    }
}