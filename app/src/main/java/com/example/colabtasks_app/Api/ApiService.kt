package com.example.colabtasks_app.Api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {

    @POST("/api/auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<Void>

    @POST("/api/signup")
    suspend fun signup(@Body signupDto: SignupDto): Response<Void>

    //lista las tareas
    @GET("/api/tasks")
    suspend fun getTasks(@Header("Authorization") token: String): Response<List<Tasks>>

    //crea una tarea
    @POST("/api/tasks/saveTask")
    suspend fun saveTask(
        @Header("Authorization") token: String,
        @Body task: CreateTask
    ): Response<Void>
}