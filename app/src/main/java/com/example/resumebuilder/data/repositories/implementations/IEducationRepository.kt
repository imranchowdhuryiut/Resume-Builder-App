package com.example.resumebuilder.data.repositories.implementations

import androidx.lifecycle.LiveData
import com.example.resumebuilder.ResumeBuilderApp
import com.example.resumebuilder.data.model.Education
import com.example.resumebuilder.data.repositories.interfaces.EducationRepository

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

class IEducationRepository : EducationRepository {
    override suspend fun saveEducation(model: Education) {
        ResumeBuilderApp.appDb.educationDao().save(model)
    }

    override suspend fun deleteEducation(model: Education) {
        ResumeBuilderApp.appDb.educationDao().delete(model)
    }

    override fun getEducationsByResumeId(resumeId: Int): LiveData<List<Education>> {
        return ResumeBuilderApp.appDb.educationDao().getEducationsByResume(resumeId)
    }


}
