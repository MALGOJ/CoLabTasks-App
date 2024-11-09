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