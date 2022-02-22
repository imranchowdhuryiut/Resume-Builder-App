package com.example.resumebuilder.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

@Parcelize
data class ResumeWrapper(
    var name: String? = null,
    var objective: String? = null,
    var specialization: String? = null,
    var mobileNumber: String? = null,
    var emailAddress: String? = null,
    var address: String? = null,
    var imagePath: String? = null,
    var workExperiences: List<WorkExperience?>? = null,
    var skills: List<Skill?>? = null,
    var educations: List<Education?>? = null,
    var projects: List<Project?>? = null
) : Parcelable
