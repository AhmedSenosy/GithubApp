package com.senosy.domain.interactor.bookmark

import com.senosy.domain.executor.PostExecutionThread
import com.senosy.domain.interactor.CompletableUseCase
import com.senosy.domain.repository.ProjectsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UnBookmarkProject @Inject constructor(
    val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnBookmarkProject.Params>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can\'t be null")
        return projectsRepository.unBookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {

        companion object {
            fun forProject(projectId: String) = Params(projectId)
        }
    }
}