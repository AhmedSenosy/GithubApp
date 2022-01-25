package com.senosy.domain.interactor.browse

import com.senosy.domain.executor.PostExecutionThread
import com.senosy.domain.interactor.ObservableUseCase
import com.senosy.domain.model.Project
import com.senosy.domain.repository.ProjectsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return  projectsRepository.getProjects()
    }

    override fun execute(observer: DisposableObserver<List<Project>>, params: Nothing?) {
        super.execute(observer, params)
    }
}