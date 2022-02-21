package com.example.resumebuilder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resumebuilder.data.db.dao.educationDao.EducationDao
import com.example.resumebuilder.data.db.dao.projectDao.ProjectDao
import com.example.resumebuilder.data.db.dao.resumeDao.ResumeDao
import com.example.resumebuilder.data.db.dao.skillDao.SkillDao
import com.example.resumebuilder.data.db.dao.workExperienceDao.WorkExperienceDao
import com.example.resumebuilder.data.model.*

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
@Database(
    entities = [
        Resume::class,
        Project::class,
        Education::class,
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
    abstract fun educationDao(): EducationDao
    abstract fun projectDao(): ProjectDao
}