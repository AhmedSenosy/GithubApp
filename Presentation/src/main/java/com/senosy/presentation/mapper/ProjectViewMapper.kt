package com.senosy.presentation.mapper

import com.senosy.domain.model.Project
import com.senosy.presentation.model.ProjectView
import javax.inject.Inject

class ProjectViewMapper @Inject constructor(): Mapper<ProjectView, Project> {
    override fun mapToView(type: Project): ProjectView {
        return with(type) {
            ProjectView(
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