package com.senosy.remote

import com.senosy.data.model.ProjectEntity
import com.senosy.data.repository.ProjectRemote
import com.senosy.remote.mapper.ProjectsResponseModelMapper
import com.senosy.remote.service.GithubTrendingService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

const val QUERY = "language:kotlin"
const val SORT_BY = "stars"
const val ORDER = "desc"

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
) : ProjectRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories(QUERY, SORT_BY, ORDER)
            .map { response -> response.items.map { mapper.mapFromModel(it) } }
    }
}