package com.example.resumebuilder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var resumeId: Int? = null,
    var projectName: String? = null,
    var teamSize: Int? = null,
    var projectSummary: String? = null,
    var technologyUsed: String? = null,
    var role: String? = null
)
