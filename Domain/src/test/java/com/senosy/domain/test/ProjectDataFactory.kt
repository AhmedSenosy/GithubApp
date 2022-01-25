package com.senosy.domain.test

import com.senosy.domain.model.Project
import java.util.*

object ProjectDataFactory {

    fun randomUUID() = UUID.randomUUID().toString()

    fun randomBoolean() = Math.random() < .5

    fun randomProject() = Project(
        randomUUID(), randomUUID(), randomUUID(), randomUUID(), randomUUID(),randomUUID(),
        randomUUID(),randomBoolean()
    )

    fun makeProjectList(count:Int):List<Project>{
        val project = mutableListOf<Project>()
         repeat(count){
            project.add(randomProject())
        }
        return project
    }
}