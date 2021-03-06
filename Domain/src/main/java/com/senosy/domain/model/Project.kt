package com.senosy.domain.model

class Project(
    val id: String, val name: String, val fullName: String,
    val starCount: String, val dateCreated: String,
    val ownerName: String, val ownerAvatar: String,
    val isBookMarked: Boolean
)