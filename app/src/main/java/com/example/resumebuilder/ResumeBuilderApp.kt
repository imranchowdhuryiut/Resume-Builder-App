package com.example.resumebuilder

import android.app.Application
import androidx.room.Room
import com.example.resumebuilder.data.db.AppDb
import com.example.resumebuilder.utils.Constants

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
class ResumeBuilderApp: Application() {

    companion object {
        lateinit var appDb: AppDb
    }

    override fun onCreate() {
        super.onCreate()
        appDb = Room.databaseBuilder(this, AppDb::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}