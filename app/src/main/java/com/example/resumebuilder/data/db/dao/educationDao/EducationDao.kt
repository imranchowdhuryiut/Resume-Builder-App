package com.example.resumebuilder.data.db.dao.educationDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.resumebuilder.data.db.dao.BaseDao
import com.example.resumebuilder.data.model.Education

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

@Dao
abstract class EducationDao: BaseDao<Education>() {

    @Query("SELECT * from educations WHERE educations.resumeId = :id")
    abstract fun getEducationsByResume(id: Int): LiveData<List<Education>>

    @Query("DELETE FROM educations WHERE educations.resumeId = :id")
    abstract fun deleteAllEducationsByResume(id: Int)

}