package com.example.colabtasks_app.Api

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupDto(
    val email: String,
    val name: String,
    val password: String
)

data class assignedUser(
    val email: String?
)

data class Tasks(
    val id: Long?,
    val title: String?,
    val description: String?,
    val status: String?,
    val priority: String?,
    val dueDate: String?,
    val updatedDate: String?,
    val proyectId: Long?,
    val assignedUser: assignedUser?
)

data class CreateTask(
    val titleDto: String,
    val descriptionDto: String,
    val statusDto: String,
    val priorityDto: String,
    val dueDateDto: String
)