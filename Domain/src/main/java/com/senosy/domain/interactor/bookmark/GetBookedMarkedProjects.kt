package com.senosy.domain.interactor.bookmark

import com.senosy.domain.executor.PostExecutionThread
import com.senosy.domain.interactor.ObservableUseCase
import com.senosy.domain.model.Project
import com.senosy.domain.repository.ProjectsRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetBookedMarkedProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}