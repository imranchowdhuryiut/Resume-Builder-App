package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.R
import com.example.resumebuilder.data.model.WorkExperience
import com.example.resumebuilder.databinding.FragmentEditExperienceBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.views.adapters.WorkExperienceAdapter
import com.google.android.material.snackbar.Snackbar

class EditExperienceFragment : Fragment(), OnItemClickCallback<WorkExperience> {

    private var _binding: FragmentEditExperienceBinding? = null

    private val mAdapter: WorkExperienceAdapter = WorkExperienceAdapter(this)

    private val mList: MutableList<WorkExperience> = mutableListOf()

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
        val companyName = _binding?.tvCompanyName?.text.toString()
        val duration = _binding?.tvDuration?.text.toString().toInt()
        clearEditView()
        mList.add(WorkExperience(companyName, duration))
        mAdapter.submitList(mList)
        mAdapter.notifyItemChanged(mList.size - 1)
    }

    private fun clearEditView() {
        _binding?.tvCompanyName?.text?.clear()
        _binding?.tvDuration?.text?.clear()
    }

    private fun validateView(view: View): Boolean {
        if (_binding?.tvCompanyName?.text?.isEmpty() == true) {
            Snackbar.make(view, "Company name must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.tvDuration?.text?.isEmpty() == true) {
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

    }

}