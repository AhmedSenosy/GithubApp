package com.senosy.data.repository

import com.senosy.data.model.ProjectEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ProjectCache {
    fun clearCache(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Observable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun areProjectCached(): Single<Boolean>

    fun setLastCachedTime(lastCache: Long): Completable

    fun isProjectCachedExpired(): Single<Boolean>
}