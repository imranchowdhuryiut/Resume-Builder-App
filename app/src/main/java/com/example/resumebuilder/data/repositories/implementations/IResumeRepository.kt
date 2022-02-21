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
        model.id?.let {
            ResumeBuilderApp.appDb.workExperiencesDao().deleteAllExperiencesByResume(it)
            ResumeBuilderApp.appDb.skillDao().deleteAllSkillsByResume(it)
            ResumeBuilderApp.appDb.educationDao().deleteAllEducationsByResume(it)
            ResumeBuilderApp.appDb.projectDao().deleteAllProjectsByResume(it)
        }
    }

    override suspend fun saveCareerObjective(objective: String, resumeId: Int) {
        ResumeBuilderApp.appDb.resumeDao().saveCareerObjectiveByResume(objective, resumeId)
    }

    override fun getCareerObjectiveByResume(resumeId: Int): LiveData<String?> {
        return ResumeBuilderApp.appDb.resumeDao().getCareerObjectiveByResume(resumeId)
    }

    override suspend fun savePersonalInfo(
        mobileNumber: String,
        email: String,
        address: String,
        filPath: String,
        resumeId: Int
    ) {
        ResumeBuilderApp.appDb.resumeDao().savePersonalInfoByResume(
            mobileNumber,
            email,
            address,
            filPath,
            resumeId
        )
    }

    override fun getPersonalInfoByResume(resumeId: Int): LiveData<Resume> {
        return ResumeBuilderApp.appDb.resumeDao().getPersonalInfoByResume(resumeId)
    }
}