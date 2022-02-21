package com.example.resumebuilder.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.resumebuilder.data.model.Education

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
object EducationItemDiff: DiffUtil.ItemCallback<Education>() {
    override fun areItemsTheSame(oldItem: Education, newItem: Education): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Education, newItem: Education): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.degree == newItem.degree &&
                oldItem.passingYear == newItem.passingYear &&
                oldItem.grade == newItem.grade
    }

}