package com.example.resumebuilder.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.resumebuilder.R
import com.example.resumebuilder.databinding.FragmentEditObjectiveBinding
import com.example.resumebuilder.viewModels.ObjectiveViewModel
import com.google.android.material.snackbar.Snackbar

class EditObjectiveFragment : Fragment() {

    private var _binding: FragmentEditObjectiveBinding? = null

    private val args by navArgs<EditObjectiveFragmentArgs>()

    private val mViewModel by viewModels<ObjectiveViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditObjectiveBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getSavedObjective()
    }

    private fun getSavedObjective() {
        mViewModel.getCareerObjective(args.resumeId).observe(viewLifecycleOwner, {data->
            if (data.isNotEmpty()) {
                _binding?.etObjective?.setText(data)
                _binding?.etObjective?.isEnabled = false
                _binding?.imgEdit?.visibility = View.VISIBLE
                _binding?.btnSave?.visibility = View.GONE
            } else {
                _binding?.imgEdit?.visibility = View.GONE
                _binding?.etObjective?.isEnabled = true
                _binding?.btnSave?.visibility = View.VISIBLE
            }
        })
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.career_objective)
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnSave.setOnClickListener {
                if (validateInput(it)) {
                    saveObjective()
                }
            }
            imgEdit.setOnClickListener {
                _binding?.imgEdit?.visibility = View.GONE
                _binding?.etObjective?.isEnabled = true
                _binding?.btnSave?.visibility = View.VISIBLE
            }
        }
    }

    private fun saveObjective() {
        val objective = _binding?.etObjective?.text?.toString() ?: ""
        mViewModel.saveCareerObjective(objective, args.resumeId)
    }

    private fun validateInput(view: View): Boolean {
        if (_binding?.etObjective?.text?.isEmpty() == true) {
            Snackbar.make(view, getString(R.string.empty_objective_msg), Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}