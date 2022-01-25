package com.senosy.data

import com.senosy.data.mapper.ProjectMapper
import com.senosy.data.repository.ProjectCache
import com.senosy.data.store.ProjectDataStoreFactory
import com.senosy.domain.model.Project
import com.senosy.domain.repository.ProjectsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ProjectDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectCache,
    private val dataStoreFactory: ProjectDataStoreFactory
) : ProjectsRepository {
    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectCached().toObservable(),
            cache.isProjectCachedExpired().toObservable(),
            { areCached, isExpired ->
                Pair(areCached, isExpired)
            })
            .flatMap {
                dataStoreFactory.getDataStore(it.first, it.second).getProjects()
            }
            .flatMap { projects ->
                dataStoreFactory.getCachedDataStore()
                    .saveProjects(projects)
                    .andThen(
                        Observable.just(projects)
                    )
            }.map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }


    override fun bookmarkProject(projectId: String): Completable {
        return dataStoreFactory.getCachedDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return dataStoreFactory.getCachedDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProject(projectId: String): Observable<List<Project>> {
        return dataStoreFactory.getCachedDataStore().getBookmarkedProjects().map {
            it.map { mapper.mapFromEntity(it) }
        }
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        TODO("Not yet implemented")
    }
}