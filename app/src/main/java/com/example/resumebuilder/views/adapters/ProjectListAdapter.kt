package com.example.resumebuilder.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.data.model.Project
import com.example.resumebuilder.databinding.ItemProjectBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.utils.ProjectItemDiff

/**
 * Created by Imran Chowdhury on 2/21/2022.
 */

class ProjectListAdapter (
    private val mCallback: OnItemClickCallback<Project>
): ListAdapter<Project, ProjectViewHolder>(ProjectItemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ProjectViewHolder(
    private val binding: ItemProjectBinding,
    private val mCallback: OnItemClickCallback<Project>? = null
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Project?) {
        mModel = item
        binding.tvProjectName.text = item?.projectName
        binding.tvProjectSummary.text = item?.projectSummary
        binding.tvRole.text = item?.role
        binding.tvTeamSize.text = item?.teamSize?.toString()
        binding.tvUsedTechnologies.text = item?.technologyUsed
    }

    private var mModel: Project? = null

    init {
        binding.imgDelete.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
    }


    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            callBack: OnItemClickCallback<Project>? = null
        ): ProjectViewHolder {
            val view = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ProjectViewHolder(view, callBack)
        }
    }
}