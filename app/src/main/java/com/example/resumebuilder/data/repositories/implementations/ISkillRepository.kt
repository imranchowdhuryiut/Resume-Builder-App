package com.example.resumebuilder.data.repositories.implementations

import androidx.lifecycle.LiveData
import com.example.resumebuilder.ResumeBuilderApp
import com.example.resumebuilder.data.model.Skill
import com.example.resumebuilder.data.repositories.interfaces.SkillRepository

/**
 * Created by Imran Chowdhury on 2/20/2022.
 */
class ISkillRepository: SkillRepository {

    override suspend fun saveSkill(model: Skill) {
        ResumeBuilderApp.appDb.skillDao().save(model)
    }

    override fun getSkillsByResumeId(resumeId: Int): LiveData<List<Skill>> {
        return ResumeBuilderApp.appDb.skillDao().getSkillsByResume(resumeId)
    }
}