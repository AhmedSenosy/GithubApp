package com.senosy.data.store

import com.senosy.data.model.ProjectEntity
import com.senosy.data.repository.ProjectCache
import com.senosy.data.repository.ProjectDataStore
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ProjectCacheDataStore @Inject constructor(
    private val cache: ProjectCache
) : ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return cache.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return cache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return cache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return cache.setProjectAsNotBookmarked(projectId)
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return cache.saveProjects(projects).andThen {
            cache.setLastCachedTime(System.currentTimeMillis())
        }
    }

    override fun clearProjects(): Completable {
        return cache.clearCache()
    }
}