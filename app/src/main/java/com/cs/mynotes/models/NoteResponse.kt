package com.cs.mynotes.models

data class NoteResponse(
//    val message: Message
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val title: String,
    val updatedAt: String,
    val userId: String
)