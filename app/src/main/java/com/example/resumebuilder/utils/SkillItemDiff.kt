package com.example.resumebuilder.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.resumebuilder.data.model.Skill
import com.example.resumebuilder.data.model.WorkExperience

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
object SkillItemDiff: DiffUtil.ItemCallback<Skill>() {
    override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
        return oldItem.skill == newItem.skill &&
                oldItem.rate == newItem.rate
    }

}