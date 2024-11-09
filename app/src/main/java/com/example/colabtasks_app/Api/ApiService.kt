package com.example.colabtasks_app.Api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("/api/auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<Void>

    @POST("/api/signup")
    suspend fun signup(@Body signupDto: SignupDto): Response<Void>
}