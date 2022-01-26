package com.senosy.presentation.state

data class Resource<out T> constructor(
    val status: ResourceState,
    val data: T? = null,
    val message: String? = null
)