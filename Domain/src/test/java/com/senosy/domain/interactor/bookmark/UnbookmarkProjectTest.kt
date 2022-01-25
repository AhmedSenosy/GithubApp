package com.senosy.domain.interactor.bookmark

import com.senosy.domain.executor.PostExecutionThread
import com.senosy.domain.repository.ProjectsRepository
import com.senosy.domain.test.ProjectDataFactory
import io.reactivex.rxjava3.core.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.lang.IllegalArgumentException

class UnbookmarkProjectTest {
    private lateinit var unbookmarkProject: UnBookmarkProject
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        unbookmarkProject = UnBookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun bookmarkComplete() {
        stubBookmarkProject(Completable.complete())
        val testCompletable = unbookmarkProject.buildUseCaseObservable(
            UnBookmarkProject.Params.forProject(ProjectDataFactory.randomUUID())
        ).test()
        testCompletable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        unbookmarkProject.buildUseCaseObservable().test()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(projectsRepository.unBookmarkProject(any())).thenReturn(completable)
    }
}