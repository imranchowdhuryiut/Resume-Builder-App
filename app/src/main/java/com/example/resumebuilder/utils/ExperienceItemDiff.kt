package com.example.resumebuilder.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.resumebuilder.data.model.WorkExperience

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
object ExperienceItemDiff: DiffUtil.ItemCallback<WorkExperience>() {
    override fun areItemsTheSame(oldItem: WorkExperience, newItem: WorkExperience): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WorkExperience, newItem: WorkExperience): Boolean {
        return oldItem.companyName == newItem.companyName &&
                oldItem.duration == newItem.duration
    }

}