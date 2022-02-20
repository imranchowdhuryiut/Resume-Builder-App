package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.data.repositories.implementations.IResumeRepository
import com.example.resumebuilder.data.repositories.interfaces.ResumeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
class PersonalInfoViewModel: ViewModel() {

    private val mRepo: ResumeRepository by lazy { IResumeRepository() }

    fun updatePersonalInfo(
        mobileNumber: String,
        email: String,
        address: String,
        filPath: String,
        resumeId: Int
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.savePersonalInfo(
                mobileNumber,
                email,
                address,
                filPath,
                resumeId
            )
        }
    }

    fun getPersonalInfoByResume(resumeId: Int): LiveData<Resume> {
        return mRepo.getPersonalInfoByResume(resumeId)
    }

}