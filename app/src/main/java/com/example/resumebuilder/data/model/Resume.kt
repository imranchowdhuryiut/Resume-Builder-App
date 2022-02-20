package com.example.resumebuilder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */

@Entity(tableName = "resumes")
data class Resume(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var objective: String? = null,
    var specialization: String? = null,
    var mobileNumber: String? = null,
    var emailAddress: String? = null,
    var address: String? = null,
    var imagePath: String? = null,
    var date: Long? = null
)
