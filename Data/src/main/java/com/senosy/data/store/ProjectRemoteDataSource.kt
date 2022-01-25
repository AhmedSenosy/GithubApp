package com.senosy.data.store

import com.senosy.data.model.ProjectEntity
import com.senosy.data.repository.ProjectDataStore
import com.senosy.data.repository.ProjectRemote
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ProjectRemoteDataSource @Inject constructor(
    private val remote : ProjectRemote
): ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return remote.getProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("This is not supported")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("This is not supported")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("This is not supported")
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("This is not supported")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("This is not supported")
    }
}