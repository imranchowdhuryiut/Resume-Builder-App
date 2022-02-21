package com.example.resumebuilder.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.resumebuilder.data.model.Project
import com.example.resumebuilder.data.model.WorkExperience

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

object ProjectItemDiff: DiffUtil.ItemCallback<Project>() {

    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.projectName == newItem.projectName &&
                oldItem.id == newItem.id &&
                oldItem.projectSummary == newItem.projectSummary &&
                oldItem.resumeId == newItem.resumeId &&
                oldItem.teamSize == newItem.teamSize &&
                oldItem.technologyUsed == newItem.technologyUsed
    }

}