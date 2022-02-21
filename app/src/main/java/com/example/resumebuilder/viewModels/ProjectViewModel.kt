package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.model.Project
import com.example.resumebuilder.data.repositories.implementations.IProjectRepository
import com.example.resumebuilder.data.repositories.interfaces.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

class ProjectViewModel : ViewModel() {

    private val mRepo: ProjectRepository by lazy { IProjectRepository() }

    fun saveProject(model: Project) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.saveProject(model)
        }
    }

    fun deleteProject(model: Project) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.deleteProject(model)
        }
    }

    fun getProjectsByResumeId(resumeId: Int): LiveData<List<Project>> {
        return mRepo.getProjectsByResumeId(resumeId)
    }

}