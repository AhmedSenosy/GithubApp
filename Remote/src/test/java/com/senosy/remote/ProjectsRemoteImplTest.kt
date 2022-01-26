package com.senosy.remote

import com.senosy.data.model.ProjectEntity
import com.senosy.data.repository.ProjectRemote
import com.senosy.remote.factory.ProjectDataFactory
import com.senosy.remote.mapper.ProjectsResponseModelMapper
import com.senosy.remote.model.ProjectModel
import com.senosy.remote.model.ProjectsResponseModel
import com.senosy.remote.service.GithubTrendingService
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    @Mock
    private lateinit var mapper: ProjectsResponseModelMapper

    @Mock
    private lateinit var service: GithubTrendingService

    private lateinit var remote: ProjectRemote

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        remote = ProjectsRemoteImpl(service, mapper)
    }

    @Test
    fun getProjectsCompletes() {
        stubGithubTrendingServiceSearchRepositories(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallServer() {
        stubGithubTrendingServiceSearchRepositories(
            Observable.just(ProjectDataFactory.makeProjectsResponse())
        )
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseModelMapperFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsWithCorrectParameter() {
        stubGithubTrendingServiceSearchRepositories(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectsResponseModelMapperFromModel(any(), ProjectDataFactory.makeProjectEntity())

        remote.getProjects().test()
        verify(service).searchRepositories(QUERY, SORT_BY, ORDER)
    }

    private fun stubGithubTrendingServiceSearchRepositories(stub: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any())) doReturn stub
    }

    private fun stubProjectsResponseModelMapperFromModel(
        model: ProjectModel,
        entity: ProjectEntity
    ) {
        whenever(mapper.mapFromModel(model)) doReturn entity
    }
}