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
import com.example.resumebuilder.data.model.Project
import com.example.resumebuilder.databinding.FragmentEditProjectBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.viewModels.ProjectViewModel
import com.example.resumebuilder.views.adapters.ProjectListAdapter
import com.google.android.material.snackbar.Snackbar

class EditProjectFragment : Fragment(), OnItemClickCallback<Project> {

    private var _binding: FragmentEditProjectBinding? = null

    private val mAdapter: ProjectListAdapter = ProjectListAdapter(this)

    private val args by navArgs<EditProjectFragmentArgs>()

    private val mViewModel by viewModels<ProjectViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditProjectBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getSavedProjectList()
    }

    private fun getSavedProjectList() {
        mViewModel.getProjectsByResumeId(args.resumeId)
            .observe(viewLifecycleOwner, {list->
                mAdapter.submitList(list)
            })
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.projects)
            btnAddProject.setOnClickListener {
                showProjectAddView()
            }
            btnSaveProject.setOnClickListener {
                if (validateInput(it)) {
                    saveProject()
                    hideProjectAddView()
                }
            }
            rvProject.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvProject.adapter = mAdapter
        }
        hideProjectAddView()
    }

    private fun hideProjectAddView() {
        _binding?.llProjectView?.visibility = View.GONE
        _binding?.btnSaveProject?.visibility = View.GONE
        _binding?.btnAddProject?.visibility = View.VISIBLE
    }

    private fun saveProject() {
        val projectName = _binding?.etProjectName?.text.toString()
        val teamSize = _binding?.etProjectTeamSize?.text.toString().toInt()
        val role = _binding?.etProjectRole?.text.toString()
        val summary = _binding?.etProjectSummary?.text.toString()
        val usedTechnology = _binding?.etProjectTechnology?.text.toString()
        clearEditView()
        val model = Project(
            resumeId = args.resumeId,
            projectName = projectName,
            teamSize = teamSize,
            role = role,
            projectSummary = summary,
            technologyUsed = usedTechnology
        )
        mViewModel.saveProject(model)
    }

    private fun clearEditView() {
        _binding?.etProjectName?.text?.clear()
        _binding?.etProjectTeamSize?.text?.clear()
        _binding?.etProjectRole?.text?.clear()
        _binding?.etProjectSummary?.text?.clear()
        _binding?.etProjectTechnology?.text?.clear()
    }

    private fun validateInput(view: View): Boolean {
        if (_binding?.etProjectName?.text?.isEmpty() == true) {
            Snackbar.make(view, "Project name must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etProjectTeamSize?.text?.isEmpty() == true) {
            Snackbar.make(view, "Team size must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etProjectRole?.text?.isEmpty() == true) {
            Snackbar.make(view, "Role must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etProjectSummary?.text?.isEmpty() == true) {
            Snackbar.make(view, "Summary must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etProjectTechnology?.text?.isEmpty() == true) {
            Snackbar.make(view, "Used technology must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun showProjectAddView() {
        _binding?.llProjectView?.visibility = View.VISIBLE
        _binding?.btnSaveProject?.visibility = View.VISIBLE
        _binding?.btnAddProject?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(model: Project) {
        mViewModel.deleteProject(model)
    }

}