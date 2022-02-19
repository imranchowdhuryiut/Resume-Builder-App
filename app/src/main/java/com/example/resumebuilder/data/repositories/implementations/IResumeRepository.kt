package com.example.resumebuilder.data.repositories.implementations

import androidx.lifecycle.LiveData
import com.example.resumebuilder.ResumeBuilderApp
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.data.repositories.interfaces.ResumeRepository

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
class IResumeRepository: ResumeRepository {

    override fun getAllSavedResumes(): LiveData<List<Resume>> {
        return ResumeBuilderApp.appDb.resumeDao().getAllResumes()
    }

    override suspend fun saveResume(model: Resume): Long {
        return ResumeBuilderApp.appDb.resumeDao().saveResume(model)
    }

    override suspend fun deleteResume(model: Resume) {
        ResumeBuilderApp.appDb.resumeDao().delete(model)
    }
}