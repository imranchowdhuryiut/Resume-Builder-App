package com.example.resumebuilder.data.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.resumebuilder.data.model.Education

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

interface EducationRepository {

    suspend fun saveEducation(model: Education)

    suspend fun deleteEducation(model: Education)

    fun getEducationsByResumeId(resumeId: Int): LiveData<List<Education>>

}