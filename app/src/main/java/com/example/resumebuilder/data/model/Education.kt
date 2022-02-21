package com.example.resumebuilder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
@Entity(tableName = "educations")
data class Education(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var resumeId: Int? = null,
    var degree: String? = null,
    var passingYear: Int? = null,
    var grade: Double? = null
)
