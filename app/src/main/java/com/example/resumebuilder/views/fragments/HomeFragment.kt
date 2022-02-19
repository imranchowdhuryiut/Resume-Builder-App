package com.example.resumebuilder.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.R
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.databinding.FragmentHomeBinding
import com.example.resumebuilder.databinding.LayoutCrateResumeBinding
import com.example.resumebuilder.utils.dateFormat
import com.example.resumebuilder.utils.onResumeClickCallback
import com.example.resumebuilder.viewModels.ResumeViewModel
import com.example.resumebuilder.views.adapters.ResumeAdapter
import java.util.*

class HomeFragment : Fragment(), onResumeClickCallback<Resume> {

    private var _binding: FragmentHomeBinding? = null

    private val mAdapter: ResumeAdapter = ResumeAdapter(this)

    private val mViewModel by viewModels<ResumeViewModel>()

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
        getSavedResumes()
        observeResumes()
    }

    private fun observeResumes() {
        mViewModel.getAllSavedResumes().observe(viewLifecycleOwner, {
            mAdapter.submitList(it)
        })
        mViewModel.resumeModel.observe(viewLifecycleOwner, { id ->
            if (id > 0) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToEditDetailsFragment(
                        id
                    )
                )
                mViewModel.setNavigated()
            }
        })
    }

    private fun getSavedResumes() {
        mViewModel.getAllSavedResumes()
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.btnBack.visibility = View.GONE
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.dashboard)
            btnAdd.setOnClickListener {
                showAddResumeView()
            }
            rvResume.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvResume.adapter = mAdapter
        }
    }

    private fun showAddResumeView() {
        val dialogView = LayoutCrateResumeBinding.inflate(layoutInflater, null, false)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView.root)
            .setCancelable(false)
            .show()
        dialogView.tvDate.text = dateFormat.format(Date())
        dialogView.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        dialogView.btnSave.setOnClickListener {
            if (validateInput(dialogView)) {
                saveResume(
                    dialogView.etName.text.toString(),
                    dialogView.etSpeciality.text.toString()
                )
                alertDialog.dismiss()
            }
        }
    }

    private fun saveResume(name: String, speciality: String) {
        val model = Resume()
        model.date = Date().time
        model.name = name
        model.specialization = speciality
        mViewModel.saveResume(model)
    }

    private fun validateInput(dialogView: LayoutCrateResumeBinding): Boolean {
        if (dialogView.etName.text?.isEmpty() == true) {
            dialogView.etName.error = "Name can not be empty"
            return false
        }
        if (dialogView.etSpeciality.text?.isEmpty() == true) {
            dialogView.etSpeciality.error = "Speciality can not be empty"
            return false
        }
        return true
    }

    override fun onDelete(model: Resume) {
        mViewModel.deleteResume(model)
    }

    override fun onEdit(model: Resume) {
        model.id?.let {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToEditDetailsFragment(it)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}