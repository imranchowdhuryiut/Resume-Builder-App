package com.example.resumebuilder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.model.Skill
import com.example.resumebuilder.data.repositories.implementations.ISkillRepository
import com.example.resumebuilder.data.repositories.interfaces.SkillRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
class SkillViewModel: ViewModel() {

    private val mRepo: SkillRepository by lazy { ISkillRepository() }

    fun saveSkill(model: Skill) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.saveSkill(model)
        }
    }

    fun deleteSkill(model: Skill) {
        viewModelScope.launch(Dispatchers.Default) {
            mRepo.deleteSkill(model)
        }
    }

    fun getSkillsByResumeId(resumeId: Int): LiveData<List<Skill>> {
        return mRepo.getSkillsByResumeId(resumeId)
    }

}