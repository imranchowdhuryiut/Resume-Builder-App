package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.model.WorkExperience
import com.example.resumebuilder.data.repositories.implementations.IWorkRepository
import com.example.resumebuilder.data.repositories.interfaces.WorkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
class WorkExperienceViewModel: ViewModel() {

    private val mRepo: WorkRepository by lazy { IWorkRepository() }

    fun saveWorkExperience(model: WorkExperience) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.saveWorkExperience(model)
        }
    }

    fun deleteWorkExperience(model: WorkExperience) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.deleteWorkExperience(model)
        }
    }

    fun getExperienceByResumeId(resumeId: Int): LiveData<List<WorkExperience>> {
        return mRepo.getWorkExperienceByResumeId(resumeId)
    }

}