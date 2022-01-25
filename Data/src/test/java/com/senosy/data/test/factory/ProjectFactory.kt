package com.senosy.data.test.factory

import com.senosy.data.model.ProjectEntity
import com.senosy.domain.model.Project

object ProjectFactory {

    fun makeProjectEntity():ProjectEntity{
        return ProjectEntity(dataFactory.randomString(),dataFactory.randomString(),
            dataFactory.randomString(),dataFactory.randomString(),dataFactory.randomString()
                ,dataFactory.randomString(),dataFactory.randomString(),dataFactory.randomBoolean())
    }

    fun makeProject():Project{
        return Project(dataFactory.randomString(),dataFactory.randomString(),
            dataFactory.randomString(),dataFactory.randomString(),dataFactory.randomString()
                ,dataFactory.randomString(),dataFactory.randomString(),dataFactory.randomBoolean())
    }
}