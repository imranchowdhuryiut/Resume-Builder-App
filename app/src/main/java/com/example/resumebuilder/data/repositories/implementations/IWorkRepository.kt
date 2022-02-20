package com.example.resumebuilder.data.repositories.implementations

import androidx.lifecycle.LiveData
import com.example.resumebuilder.ResumeBuilderApp
import com.example.resumebuilder.data.model.WorkExperience
import com.example.resumebuilder.data.repositories.interfaces.WorkRepository

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
class IWorkRepository: WorkRepository {

    override suspend fun saveWorkExperience(model: WorkExperience) {
        ResumeBuilderApp.appDb.workExperiencesDao().save(model)
    }

    override suspend fun deleteWorkExperience(model: WorkExperience) {
        ResumeBuilderApp.appDb.workExperiencesDao().delete(model)
    }

    override fun getWorkExperienceByResumeId(resumeId: Int): LiveData<List<WorkExperience>> {
        return ResumeBuilderApp.appDb.workExperiencesDao().getWorkExperiencesByResume(resumeId)
    }
}