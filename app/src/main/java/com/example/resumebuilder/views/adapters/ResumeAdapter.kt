package com.example.resumebuilder.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.data.model.Resume
import com.example.resumebuilder.databinding.ItemResumeBinding
import com.example.resumebuilder.utils.ResumeItemDiff
import com.example.resumebuilder.utils.dateFormat
import com.example.resumebuilder.utils.onResumeClickCallback
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */

class ResumeAdapter (
    private val mCallBack: onResumeClickCallback<Resume>? = null
): ListAdapter<Resume, ResumeViewHolder>(ResumeItemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeViewHolder {
        return ResumeViewHolder.createViewHolder(parent, mCallBack)
    }

    override fun onBindViewHolder(holder: ResumeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ResumeViewHolder(
    private val binding: ItemResumeBinding,
    private val mCallBack: onResumeClickCallback<Resume>? = null
): RecyclerView.ViewHolder(binding.root) {

    private var mModel: Resume? = null

    init {
        binding.btnEdit.setOnClickListener {
            mModel?.let { it1 -> mCallBack?.onEdit(it1) }
        }
        binding.btnDelete.setOnClickListener {
            mModel?.let { it1 -> mCallBack?.onDelete(it1) }
        }
    }

    fun bind(item: Resume?) {
        mModel = item
        binding.tvResumeName.text = item?.name
        binding.tvResumeSpeciality.text = item?.specialization
        binding.tvResumeDate.text = dateFormat.format(Date(item?.date ?: 0))
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            mCallBack: onResumeClickCallback<Resume>? = null
        ): ResumeViewHolder {
            val view = ItemResumeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ResumeViewHolder(view, mCallBack)
        }
    }
}