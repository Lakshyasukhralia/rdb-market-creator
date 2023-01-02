package com.sukhralia.enjoin.feature.auth.data.remote.dto

data class UserRecordDto(
    val avatar: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val email: String,
    val emailVisibility: Boolean,
    val id: String,
    val name: String,
    val updated: String,
    val username: String,
    val verified: Boolean
)