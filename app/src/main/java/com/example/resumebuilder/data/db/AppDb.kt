package com.example.resumebuilder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resumebuilder.data.db.dao.resumeDao.ResumeDao
import com.example.resumebuilder.data.db.dao.skillDao.SkillDao
import com.example.resumebuilder.data.db.dao.workExperienceDao.WorkExperienceDao
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.data.model.Skill
import com.example.resumebuilder.data.model.WorkExperience

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
@Database(
    entities = [
        Resume::class,
        WorkExperience::class,
        Skill::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase() {
    abstract fun resumeDao(): ResumeDao
    abstract fun workExperiencesDao(): WorkExperienceDao
    abstract fun skillDao(): SkillDao
}