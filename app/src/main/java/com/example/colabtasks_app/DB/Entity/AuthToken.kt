package com.example.colabtasks_app.DB.Entity

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_token")
data class AuthToken(
    @PrimaryKey(autoGenerate = true) val idToken: Long = 0,
    val token: String
)
