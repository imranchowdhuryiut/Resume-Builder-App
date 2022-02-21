package com.example.resumebuilder.data.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.resumebuilder.data.model.Project

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */
interface ProjectRepository {

    suspend fun saveProject(model: Project)

    suspend fun deleteProject(model: Project)

    fun getProjectsByResumeId(resumeId: Int): LiveData<List<Project>>

}