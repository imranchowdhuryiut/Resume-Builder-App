package com.example.resumebuilder.views.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.resumebuilder.R
import com.example.resumebuilder.databinding.FragmentPersonalDetailsBinding
import com.example.resumebuilder.utils.getRealPathFromURI
import com.example.resumebuilder.utils.loadImagesWithGlide
import com.example.resumebuilder.utils.showGrantPermissionAlertDialog
import com.example.resumebuilder.viewModels.PersonalInfoViewModel
import com.google.android.material.snackbar.Snackbar

class EditPersonalDetailsFragment : Fragment() {

    private var _binding: FragmentPersonalDetailsBinding? = null

    private val args by navArgs<EditPersonalDetailsFragmentArgs>()

    private var imagePath: String? = null

    private val mViewModel by viewModels<PersonalInfoViewModel>()

    private val launchActivityForResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Uri? = result.data?.data
                imagePath = data?.let { requireContext().getRealPathFromURI(it) }
                imagePath?.let { _binding?.imgProfile?.loadImagesWithGlide(it) }
            }
        }

    private val askPermissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                fireImageSelectIntent()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    askPermission()
                } else {
                    requireActivity().showGrantPermissionAlertDialog(
                        getString(R.string.storage),
                        getString(R.string.open_settings)
                    ) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts(
                            "package",
                            requireActivity().packageName,
                            this::class.simpleName.toString()
                        )
                        intent.data = uri
                        launchActivityForResult.launch(intent)
                    }
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonalDetailsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getPersonalInfo()
    }

    private fun getPersonalInfo() {
        mViewModel.getPersonalInfoByResume(args.resumeId)
            .observe(viewLifecycleOwner, { data ->
                if (!data.emailAddress.isNullOrEmpty() or !data.imagePath.isNullOrEmpty()) {
                    _binding?.imgEdit?.visibility = View.VISIBLE
                    _binding?.etAddress?.isEnabled = false
                    _binding?.etAddress?.setText(data.address)
                    _binding?.etEmail?.isEnabled = false
                    _binding?.etEmail?.setText(data.emailAddress)
                    _binding?.etMobileNumber?.isEnabled = false
                    _binding?.etMobileNumber?.setText(data.mobileNumber)
                    _binding?.imgProfile?.loadImagesWithGlide(data.imagePath)
                    imagePath = data.imagePath
                    _binding?.btnChooseImage?.visibility = View.GONE
                    _binding?.btnSavePersonalInfo?.visibility = View.GONE
                } else {
                    _binding?.imgEdit?.visibility = View.GONE
                    _binding?.etAddress?.isEnabled = true
                    _binding?.etEmail?.isEnabled = true
                    _binding?.etMobileNumber?.isEnabled = true
                    _binding?.btnChooseImage?.visibility = View.VISIBLE
                    _binding?.btnSavePersonalInfo?.visibility = View.VISIBLE
                }
            })
    }

    private fun initView() {
        _binding?.apply {
            layoutCustomToolbar.tvToolbarTitle.text = getString(R.string.personal_details)
            layoutCustomToolbar.btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnChooseImage.setOnClickListener {
                chooseImage()
            }
            imgEdit.setOnClickListener {
                showEditView()
            }
            btnSavePersonalInfo.setOnClickListener {
                if (validateInput(it)) {
                    savePersonalInfo()
                }
            }
        }
    }

    private fun showEditView() {
        _binding?.imgEdit?.visibility = View.GONE
        _binding?.btnChooseImage?.visibility = View.VISIBLE
        _binding?.btnSavePersonalInfo?.visibility = View.VISIBLE
        _binding?.etMobileNumber?.isEnabled = true
        _binding?.etEmail?.isEnabled = true
        _binding?.etAddress?.isEnabled = true
    }

    private fun chooseImage() {
        if (checkStoragePermission()) {
            fireImageSelectIntent()
        } else {
            askPermission()
        }
    }

    private fun askPermission() {
        askPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun fireImageSelectIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_PICK
        launchActivityForResult.launch(intent)
    }

    private fun checkStoragePermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun savePersonalInfo() {
        val mobileNumber = _binding?.etMobileNumber?.text?.toString() ?: ""
        val email = _binding?.etEmail?.text?.toString() ?: ""
        val address = _binding?.etAddress?.text?.toString() ?: ""
        val filPath = imagePath!!
        mViewModel.updatePersonalInfo(
            mobileNumber,
            email,
            address,
            filPath,
            args.resumeId
        )
    }

    private fun validateInput(view: View): Boolean {
        if (_binding?.etMobileNumber?.text?.isEmpty() == true) {
            Snackbar.make(view, "Mobile number must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etAddress?.text?.isEmpty() == true) {
            Snackbar.make(view, "Address must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (_binding?.etEmail?.text?.isEmpty() == true) {
            Snackbar.make(view, "Email must not be empty", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (imagePath.isNullOrEmpty()) {
            Snackbar.make(view, "Choose an Image", Snackbar.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}