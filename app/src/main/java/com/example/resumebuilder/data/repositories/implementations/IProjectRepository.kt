package com.example.resumebuilder.data.repositories.implementations

import androidx.lifecycle.LiveData
import com.example.resumebuilder.ResumeBuilderApp
import com.example.resumebuilder.data.model.Project
import com.example.resumebuilder.data.repositories.interfaces.ProjectRepository

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */


class IProjectRepository : ProjectRepository {

    override suspend fun saveProject(model: Project) {
        ResumeBuilderApp.appDb.projectDao().save(model)
    }

    override suspend fun deleteProject(model: Project) {
        ResumeBuilderApp.appDb.projectDao().delete(model)
    }

    override fun getProjectsByResumeId(resumeId: Int): LiveData<List<Project>> {
        return ResumeBuilderApp.appDb.projectDao().getProjectsByResume(resumeId)
    }


}