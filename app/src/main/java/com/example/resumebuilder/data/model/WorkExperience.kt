package com.example.resumebuilder.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * Created by Imran Chowdhury on 2/18/2022.
 */
@Parcelize
@Entity(tableName = "work_experiences")
data class WorkExperience(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var resumeId: Int? = null,
    var companyName: String? = null,
    var duration: Int? = null
) : Parcelable
