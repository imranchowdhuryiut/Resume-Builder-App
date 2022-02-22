package com.example.resumebuilder.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
@Parcelize
@Entity(tableName = "skills")
data class Skill(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var resumeId: Int? = null,
    var skill: String? = null,
    var rate: String? = null
) : Parcelable
