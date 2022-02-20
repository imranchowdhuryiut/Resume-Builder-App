package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.repositories.implementations.IResumeRepository
import com.example.resumebuilder.data.repositories.interfaces.ResumeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
class ObjectiveViewModel: ViewModel() {

    private val mRepo: ResumeRepository by lazy { IResumeRepository() }

    fun saveCareerObjective(objective: String, resumeId: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.saveCareerObjective(objective, resumeId)
        }
    }

    fun getCareerObjective(resumeId: Int): LiveData<String> {
        return mRepo.getCareerObjectiveByResume(resumeId)
    }
}