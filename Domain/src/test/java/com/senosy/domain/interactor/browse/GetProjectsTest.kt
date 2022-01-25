package com.senosy.domain.interactor.browse

import com.senosy.domain.executor.PostExecutionThread
import com.senosy.domain.interactor.browse.GetProjects
import com.senosy.domain.model.Project
import com.senosy.domain.repository.ProjectsRepository
import com.senosy.domain.test.ProjectDataFactory
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class GetProjectsTest {
    private lateinit var getProjects: GetProjects

    @Mock
    lateinit var projectsRepository: ProjectsRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    private fun stubProjects(observable: Observable<List<Project>>) {
        `when`(projectsRepository.getProjects()).thenReturn(observable)
    }

    @Test
    fun getProjectsComplete() {
        stubProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubProjects(Observable.just(projects))
        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }
}