package com.senosy.data.test.store

import com.senosy.data.store.ProjectCacheDataStore
import com.senosy.data.store.ProjectDataStoreFactory
import com.senosy.data.store.ProjectRemoteDataSource
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

class ProjectDataStoreFactoryTest {
    private val cacheDataStore = mock<ProjectCacheDataStore>()
    private val remoteDataStore = mock<ProjectRemoteDataSource>()
    private val factory = ProjectDataStoreFactory(cacheDataStore,remoteDataStore)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getDataStoreReturnRemoteStoreWhenCacheExpired() {
        assertEquals(remoteDataStore,factory.getDataStore(projectCached = true, cacheExpired = true))
    }

    @Test
    fun getDataStoreReturnRemoteStoreWhenCacheNotExpired() {
        assertEquals(remoteDataStore,factory.getDataStore(projectCached = false, cacheExpired = false))
    }

    @Test
    fun getDataStoreReturnCacheStore() {
        assertEquals(cacheDataStore,factory.getDataStore(projectCached = true, cacheExpired = false))
    }

    @Test
    fun getCacheDataStoreReturnCacheStore() {
        assertEquals(cacheDataStore,factory.getCachedDataStore())
    }
}