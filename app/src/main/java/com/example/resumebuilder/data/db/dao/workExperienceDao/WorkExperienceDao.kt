package com.example.resumebuilder.data.db.dao.workExperienceDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.resumebuilder.data.db.dao.BaseDao
import com.example.resumebuilder.data.model.WorkExperience

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
@Dao
abstract class WorkExperienceDao: BaseDao<WorkExperience>() {

    @Query("SELECT * FROM work_experiences WHERE work_experiences.resumeId = :id")
    abstract fun getWorkExperiencesByResume(id: Int): LiveData<List<WorkExperience>>

    @Query("DELETE FROM work_experiences WHERE work_experiences.resumeId = :id")
    abstract fun deleteAllExperiencesByResume(id: Int)

    @Query("SELECT * FROM work_experiences WHERE work_experiences.resumeId = :id")
    abstract fun getWorkExperiencesByResumeAsync(id: Int): List<WorkExperience?>

}