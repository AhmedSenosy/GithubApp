package com.senosy.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senosy.cache.dao.CachedProjectsDao
import com.senosy.cache.dao.ConfigDao
import com.senosy.cache.model.CachedProject
import com.senosy.cache.model.Config
import com.senosy.cache.model.SingletonHolder

@Database(
    entities = [
        CachedProject::class,
        Config::class],
    version = 1,
    exportSchema = false)
abstract class ProjectsDatabase : RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao

    companion object : SingletonHolder<Context, ProjectsDatabase>({
        Room.databaseBuilder(
            it.applicationContext,
            ProjectsDatabase::class.java,
            "projects.db"
        ).build()
    })
}