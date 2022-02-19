package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.data.repositories.implementations.IResumeRepository
import com.example.resumebuilder.data.repositories.interfaces.ResumeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
class ResumeViewModel: ViewModel() {

    private val mRepo: ResumeRepository by lazy { IResumeRepository() }

    private val _resumeModel = MutableLiveData<Int>()
    val resumeModel: LiveData<Int> = _resumeModel

    fun getAllSavedResumes(): LiveData<List<Resume>> {
        return mRepo.getAllSavedResumes()
    }

    fun saveResume(model: Resume) {
        viewModelScope.launch(Dispatchers.Default) {
            val data = mRepo.saveResume(model)
            _resumeModel.postValue(data.toInt())
        }
    }

    fun deleteResume(model: Resume) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.deleteResume(model)
        }
    }

    fun setNavigated() {
        _resumeModel.postValue(-1)
    }

}