package com.example.resumebuilder.data.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.resumebuilder.data.model.Resume

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
interface ResumeRepository {

    fun getAllSavedResumes(): LiveData<List<Resume>>

    suspend fun saveResume(model: Resume): Long

    suspend fun deleteResume(model: Resume)

    suspend fun saveCareerObjective(objective: String, resumeId: Int)

    fun getCareerObjectiveByResume(resumeId: Int): LiveData<String?>

    suspend fun savePersonalInfo(
        mobileNumber: String,
        email: String,
        address: String,
        filPath: String,
        resumeId: Int
    )

    fun getPersonalInfoByResume(resumeId: Int): LiveData<Resume>

}