package com.example.resumebuilder.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.data.model.WorkExperience
import com.example.resumebuilder.databinding.ItemExperienceBinding
import com.example.resumebuilder.utils.ExperienceItemDiff
import com.example.resumebuilder.utils.OnItemClickCallback

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */

class WorkExperienceAdapter(
    private var mCallback: OnItemClickCallback<WorkExperience>? = null
): ListAdapter<WorkExperience, WorkExperienceViewHolder>(ExperienceItemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkExperienceViewHolder {
        return WorkExperienceViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: WorkExperienceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class WorkExperienceViewHolder(
    private val binding: ItemExperienceBinding,
    private val mCallback: OnItemClickCallback<WorkExperience>?
): RecyclerView.ViewHolder(binding.root) {

    private var mModel: WorkExperience? = null

    init {
        binding.imgDelete.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
    }

    fun bind(item: WorkExperience?) {
        mModel = item
        binding.tvCompanyName.text = item?.companyName
        binding.tvDuration.text = item?.duration.toString()
    }


    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            mCallback: OnItemClickCallback<WorkExperience>? = null
        ): WorkExperienceViewHolder {
            val view = ItemExperienceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return WorkExperienceViewHolder(view, mCallback)
        }
    }

}