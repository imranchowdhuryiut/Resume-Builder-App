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

    @Query("UPDATE resumes SET objective = :objective WHERE id = :resumeId")
    abstract fun saveCareerObjectiveByResume(objective: String, resumeId: Int)

    @Query("SELECT objective FROM resumes WHERE id = :resumeId")
    abstract fun getCareerObjectiveByResume(resumeId: Int): LiveData<String?>

    @Query("UPDATE resumes SET " +
            "mobileNumber = :mobileNumber, " +
            "emailAddress = :email, " +
            "address = :address, " +
            "imagePath = :filPath " +
            "WHERE id = :resumeId"
    )
    abstract fun savePersonalInfoByResume(
        mobileNumber: String,
        email: String,
        address: String,
        filPath: String,
        resumeId: Int
    )

    @Query("SELECT * FROM resumes WHERE id = :resumeId")
    abstract fun getPersonalInfoByResume(resumeId: Int): LiveData<Resume>

    //SELECT resumes.id, resumes.name, resumes.objective, skills.skill, skills.rate FROM resumes JOIN skills on resumes.id = skills.resumeId

}