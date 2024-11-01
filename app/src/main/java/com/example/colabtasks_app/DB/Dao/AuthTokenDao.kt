package com.example.colabtasks_app.DB.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.colabtasks_app.DB.Entity.AuthToken

@Dao
interface AuthTokenDao {
    @Insert
    suspend fun insertToken(token: AuthToken)

    @Query("SELECT * FROM auth_token")
    suspend fun getToken(): AuthToken

    // buscar por el id
    @Query("SELECT * FROM auth_token WHERE idToken = :idToken")
    suspend fun getToken(idToken: Long): AuthToken

    @Query("UPDATE auth_token SET token = :token WHERE idToken = :idToken")
    suspend fun updateToken(idToken: Long, token: String)

    //eliminar por el id
    @Query("DELETE FROM auth_token WHERE idToken = :idToken")
    suspend fun deleteToken(idToken: Long)
}
