package com.example.resumebuilder.data.db.dao.projectDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.resumebuilder.data.db.dao.BaseDao
import com.example.resumebuilder.data.model.Project
import com.example.resumebuilder.data.model.Skill

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

@Dao
abstract class ProjectDao: BaseDao<Project>() {

    @Query("SELECT * from projects WHERE projects.resumeId = :id")
    abstract fun getProjectsByResume(id: Int): LiveData<List<Project>>

    @Query("DELETE FROM projects WHERE projects.resumeId = :id")
    abstract fun deleteAllProjectsByResume(id: Int)

    @Query("SELECT * from projects WHERE projects.resumeId = :id")
    abstract fun deleteAllProjectsByResumeAsync(id: Int): List<Project?>

}