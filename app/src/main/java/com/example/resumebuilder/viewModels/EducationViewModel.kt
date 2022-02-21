package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.model.Education
import com.example.resumebuilder.data.repositories.implementations.IEducationRepository
import com.example.resumebuilder.data.repositories.interfaces.EducationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

class EducationViewModel : ViewModel() {

    private val mRepo: EducationRepository by lazy { IEducationRepository() }

    fun saveEducation(model: Education) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.saveEducation(model)
        }
    }

    fun deleteEducation(model: Education) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.deleteEducation(model)
        }
    }

    fun getEducationByResumeId(resumeId: Int): LiveData<List<Education>> {
        return mRepo.getEducationsByResumeId(resumeId)
    }

}