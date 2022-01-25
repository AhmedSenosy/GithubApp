package com.senosy.data.test

import com.senosy.data.ProjectDataRepository
import com.senosy.data.mapper.ProjectMapper
import com.senosy.data.model.ProjectEntity
import com.senosy.data.repository.ProjectCache
import com.senosy.data.repository.ProjectDataStore
import com.senosy.data.store.ProjectDataStoreFactory
import com.senosy.data.test.factory.ProjectFactory
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ProjectDataRepositoryTest {
    private val mapper = mock<ProjectMapper>()
    private val factory = mock<ProjectDataStoreFactory>()
    private val store = mock<ProjectDataStore>()
    private val cache = mock<ProjectCache>()
    private val repository = ProjectDataRepository(mapper, cache, factory)

    @Before
    fun setUp() {
        stubFactory()
    }

    private fun stubMapper(model: com.senosy.domain.model.Project, entity: ProjectEntity) {
        whenever(mapper.mapFromEntity(entity)).thenReturn(model)
    }

    private fun stubFactory() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }

    private fun stubProject(observable: Observable<List<ProjectEntity>>) {
        whenever(store.getProjects())
            .thenReturn(observable)
    }

    @Test
    fun getProjectsComplete() {
        stubProject(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        stubMapper(ProjectFactory.makeProject(), any())
        val testObservable = repository.getProjects().test()
        testObservable.assertComplete()
    }
}