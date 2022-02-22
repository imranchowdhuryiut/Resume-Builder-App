package com.example.resumebuilder.viewModels

import androidx.lifecycle.*
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.data.model.ResumeWrapper
import com.example.resumebuilder.data.repositories.implementations.IResumeRepository
import com.example.resumebuilder.data.repositories.interfaces.ResumeRepository
import com.example.resumebuilder.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
class ResumeViewModel : ViewModel() {

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

    fun getResume(resumeId: Int): LiveData<Resource<ResumeWrapper>> {
        return liveData(Dispatchers.Default) {
            emit(Resource.loading(null))
            val data = mRepo.getFullResume(resumeId)
            if (data.address.isNullOrEmpty() || data.name.isNullOrEmpty() || data.imagePath.isNullOrEmpty()) {
                emit(Resource.error("No personal information found", null))
            }else if (data.objective.isNullOrEmpty()) {
                emit(Resource.error("No career objective found", null))
            } else if (data.workExperiences == null || data.workExperiences?.isEmpty() == true) {
                emit(Resource.error("No work experience found", null))
            } else if (data.skills == null || data.skills?.isEmpty() == true) {
                emit(Resource.error("No skills found", null))
            } else if (data.educations == null || data.educations?.isEmpty() == true) {
                emit(Resource.error("No educations found", null))
            } else if (data.projects == null || data.projects?.isEmpty() == true) {
                emit(Resource.error("No projects found", null))
            } else {
                emit(Resource.success(data))
            }
        }
    }

}