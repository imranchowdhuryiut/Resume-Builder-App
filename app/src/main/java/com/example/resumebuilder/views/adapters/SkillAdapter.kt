package com.example.resumebuilder.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.resumebuilder.data.model.Skill
import com.example.resumebuilder.databinding.ItemSkillBinding
import com.example.resumebuilder.utils.OnItemClickCallback
import com.example.resumebuilder.utils.SkillItemDiff

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */

class SkillAdapter (
    private var mCallback: OnItemClickCallback<Skill>? = null
): ListAdapter<Skill, SkillViewHolder>(SkillItemDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        return SkillViewHolder.createViewHolder(parent, mCallback)
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class SkillViewHolder(
    private val binding: ItemSkillBinding,
    private val mCallback: OnItemClickCallback<Skill>? = null
): RecyclerView.ViewHolder(binding.root) {

    private var mModel: Skill? = null

    init {
        binding.imgDelete.setOnClickListener {
            mModel?.let { it1 -> mCallback?.onClick(it1) }
        }
    }

    fun bind(item: Skill?) {
        mModel = item
        binding.tvSkill.text = item?.skill
        binding.tvRate.text = item?.rate
    }

    companion object {
        fun createViewHolder(
            parent: ViewGroup,
            mCallback: OnItemClickCallback<Skill>? = null
        ): SkillViewHolder {
            val view = ItemSkillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SkillViewHolder(view, mCallback)
        }
    }

}