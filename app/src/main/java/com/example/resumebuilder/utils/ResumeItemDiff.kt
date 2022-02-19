package com.example.resumebuilder.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.data.model.Skill

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */

object ResumeItemDiff: DiffUtil.ItemCallback<Resume>() {
    override fun areItemsTheSame(oldItem: Resume, newItem: Resume): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Resume, newItem: Resume): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.date == newItem.date &&
                oldItem.specialization == newItem.specialization
    }

}