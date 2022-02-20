package com.example.resumebuilder.data.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.resumebuilder.data.model.WorkExperience

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
interface WorkRepository {

    suspend fun saveWorkExperience(model: WorkExperience)

    suspend fun deleteWorkExperience(model: WorkExperience)

    fun getWorkExperienceByResumeId(resumeId: Int): LiveData<List<WorkExperience>>

}