package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.minusAssign
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.R
import com.example.resumebuilder.data.model.Skill
import com.example.resumebuilder.databinding.FragmentEditSkillsBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.views.adapters.SkillAdapter
import com.google.android.material.snackbar.Snackbar

class EditSkillsFragment : Fragment(), OnItemClickCallback<Skill> {

    private var _binding: FragmentEditSkillsBinding? = null

    private val mList: MutableList<Skill> = mutableListOf()

    private val mAdapter: SkillAdapter = SkillAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditSkillsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.skills)
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnAddSkill.setOnClickListener {
                showSkillAddView()
            }
            btnSaveSkill.setOnClickListener {
                if (validateInput(it)) {
                    saveSkill()
                    hideSkillView()
                }
            }
            rvSkills.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            rvSkills.adapter = mAdapter
        }
        hideSkillView()
    }

    private fun saveSkill() {
        val skillName = _binding?.etSkillName?.text.toString()
        val rate = _binding?.root?.findViewById<RadioButton>(_binding?.rgSkill?.checkedRadioButtonId!!)?.text.toString()
        clearEditView()
        mList.add(Skill(skill = skillName, rate = rate))
        mAdapter.submitList(mList)
        mAdapter.notifyItemChanged(mList.size - 1)
    }

    private fun clearEditView() {
        _binding?.etSkillName?.text?.clear()
        _binding?.rgSkill?.clearCheck()
    }

    private fun validateInput(view: View): Boolean {
        if (_binding?.etSkillName?.text?.isEmpty() == true) {
            Snackbar.make(view, "Skill must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.rgSkill?.checkedRadioButtonId == -1) {
            Snackbar.make(view, "Rating must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun hideSkillView() {
        _binding?.llSkillView?.visibility = View.GONE
        _binding?.btnSaveSkill?.visibility = View.GONE
        _binding?.btnAddSkill?.visibility = View.VISIBLE
    }

    private fun showSkillAddView() {
        _binding?.llSkillView?.visibility = View.VISIBLE
        _binding?.btnSaveSkill?.visibility = View.VISIBLE
        _binding?.btnAddSkill?.visibility = View.GONE
    }

    override fun onClick(model: Skill) {

    }

}