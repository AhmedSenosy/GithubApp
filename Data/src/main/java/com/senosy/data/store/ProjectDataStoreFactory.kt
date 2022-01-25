package com.senosy.data.store

import com.senosy.data.repository.ProjectDataStore
import javax.inject.Inject

open class ProjectDataStoreFactory @Inject constructor(
    private val projectCacheDataStore: ProjectCacheDataStore,
    private val projectRemoteDataSource: ProjectRemoteDataSource
) {
    open fun getDataStore(projectCached: Boolean, cacheExpired: Boolean): ProjectDataStore {
        return if (projectCached && !cacheExpired) {
            projectCacheDataStore
        } else {
            projectRemoteDataSource
        }
    }

    open fun getCachedDataStore(): ProjectCacheDataStore = projectCacheDataStore
}