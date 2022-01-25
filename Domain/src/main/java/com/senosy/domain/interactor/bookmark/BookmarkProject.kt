package com.senosy.domain.interactor.bookmark

import com.senosy.domain.executor.PostExecutionThread
import com.senosy.domain.interactor.CompletableUseCase
import com.senosy.domain.repository.ProjectsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class BookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can\'t be null")

        return projectsRepository.bookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {

        companion object {
            fun forProject(projectId: String) = Params(projectId)
        }
    }
}