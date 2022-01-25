package com.senosy.data.repository

import com.senosy.data.model.ProjectEntity
import io.reactivex.rxjava3.core.Observable

interface ProjectRemote {
    fun getProjects(): Observable<List<ProjectEntity>>
}