package com.example.resumebuilder.data.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.resumebuilder.data.model.Skill

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
interface SkillRepository {

    suspend fun saveSkill(model: Skill)
    fun getSkillsByResumeId(resumeId: Int): LiveData<List<Skill>>

}