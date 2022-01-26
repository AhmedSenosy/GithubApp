package com.senosy.cache.mapper

import com.senosy.cache.model.CachedProject
import com.senosy.data.model.ProjectEntity
import javax.inject.Inject

class CachedProjectMapper @Inject constructor() : CacheMapper<CachedProject, ProjectEntity> {

    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return with(type) {
            ProjectEntity(
                id,
                name,
                fullName,
                starCount,
                dateCreated,
                ownerName,
                ownerAvatar,
                isBookmarked
            )
        }
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return with(type) {
            CachedProject(
                id,
                name,
                fullName,
                starCount,
                dateCreated,
                ownerName,
                ownerAvatar,
                isBookMarked
            )
        }
    }
}