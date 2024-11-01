package com.example.colabtasks_app.DB.Repository

import com.example.colabtasks_app.DB.Dao.AuthTokenDao
import com.example.colabtasks_app.DB.Entity.AuthToken


class AuthTokenRepository(private val authTokenDao: AuthTokenDao) {

    suspend fun insertAuthToken(token: AuthToken) = authTokenDao.insertToken(token)

    suspend fun getAuthToken() = authTokenDao.getToken()

    suspend fun getAuthToken(idToken: Long) = authTokenDao.getToken(idToken)

    suspend fun updateAuthToken(idToken: Long, token: String) = authTokenDao.updateToken(idToken, token)

    suspend fun deleteAuthToken(idToken: Long) = authTokenDao.deleteToken(idToken)
}