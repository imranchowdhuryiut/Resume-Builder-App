package com.example.resumebuilder.data.db.dao.resumeDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.resumebuilder.data.db.dao.BaseDao
import com.example.resumebuilder.data.model.Resume

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
@Dao
abstract class ResumeDao: BaseDao<Resume>() {

    @Query("SELECT * from resumes")
    abstract fun getAllResumes(): LiveData<List<Resume>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveResume(model: Resume): Long

}