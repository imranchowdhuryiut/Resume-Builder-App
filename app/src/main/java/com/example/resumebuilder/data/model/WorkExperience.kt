package com.example.resumebuilder.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/**
 * Created by Imran Chowdhury on 2/18/2022.
 */
@Entity(tableName = "work_experiences")
data class WorkExperience(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var resumeId: Int? = null,
    var companyName: String? = null,
    var duration: Int? = null
)
