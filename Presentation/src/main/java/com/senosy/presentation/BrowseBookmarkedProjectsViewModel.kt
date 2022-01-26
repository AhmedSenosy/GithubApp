package com.senosy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senosy.domain.interactor.bookmark.GetBookedMarkedProjects
import com.senosy.domain.model.Project
import com.senosy.presentation.mapper.ProjectViewMapper
import com.senosy.presentation.model.ProjectView
import com.senosy.presentation.state.Resource
import com.senosy.presentation.state.ResourceState
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject


class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookedMarkedProjects,
    private val mapper: ProjectViewMapper
) : ViewModel() {


    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING))
        getBookmarkedProjects.execute(ProjectsSubscriber())
    }

    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Project>) {
            liveData.postValue(Resource(
                ResourceState.SUCCESS,
                data = t.map { mapper.mapToView(it) }))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(
                ResourceState.ERROR,
                message = e.localizedMessage))
        }
    }
}