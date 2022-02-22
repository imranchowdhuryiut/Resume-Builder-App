package com.example.resumebuilder.data.db.dao.skillDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.resumebuilder.data.db.dao.BaseDao
import com.example.resumebuilder.data.model.Skill

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
@Dao
abstract class SkillDao: BaseDao<Skill>() {

    @Query("SELECT * from skills WHERE skills.resumeId = :id")
    abstract fun getSkillsByResume(id: Int): LiveData<List<Skill>>

    @Query("DELETE FROM skills WHERE skills.resumeId = :id")
    abstract fun deleteAllSkillsByResume(id: Int)

    @Query("SELECT * from skills WHERE skills.resumeId = :id")
    abstract fun getSkillsByResumeAsync(id: Int): List<Skill?>

}