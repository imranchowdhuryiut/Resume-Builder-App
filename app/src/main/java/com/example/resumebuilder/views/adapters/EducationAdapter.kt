package com.example.resumebuilder.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.data.model.Education
import com.example.resumebuilder.databinding.ItemEducationBinding
import com.example.resumebuilder.utils.EducationItemDiff
import com.example.resumebuilder.utils.OnItemClickCallback

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */

class EducationAdapter (
    private var mCallback:OnItemClickCallback<Education>? = null
): ListAdapter<Education, EducationViewHolder>(EducationItemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        return EducationViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class EducationViewHolder(
    private val binding: ItemEducationBinding,
    private val mCallback: OnItemClickCallback<Education>? = null
): RecyclerView.ViewHolder(binding.root) {

    private var mModel: Education? = null

    init {
        binding.imgDelete.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
    }


    fun bind(item: Education?) {
        mModel = item
        binding.tvDegree.text = item?.degree
        binding.tvGrade.text = item?.grade.toString()
        binding.tvPassingYear.text = item?.passingYear.toString()
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            mCallback: OnItemClickCallback<Education>? = null
        ): EducationViewHolder {
            val view = ItemEducationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return EducationViewHolder(view, mCallback)
        }
    }

}