package com.example.resumebuilder.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
@Parcelize
@Entity(tableName = "educations")
data class Education(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var resumeId: Int? = null,
    var degree: String? = null,
    var passingYear: Int? = null,
    var grade: Double? = null
) : Parcelable
