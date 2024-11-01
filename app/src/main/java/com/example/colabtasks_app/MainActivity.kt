package com.example.colabtasks_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.colabtasks_app.DB.CoLabTasksDataBase
import com.example.colabtasks_app.DB.Dao.AuthTokenDao
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import com.example.colabtasks_app.screens.LoginScreen
import com.example.colabtasks_app.ui.theme.CoLabTasksAppTheme

class MainActivity : ComponentActivity() {

    // Dao
    private lateinit var authTokenDao: AuthTokenDao

    // Repository
    private lateinit var authTokenRepository: AuthTokenRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = CoLabTasksDataBase.getDatabase(applicationContext)

        authTokenDao = db.authTokenDao()
        authTokenRepository = AuthTokenRepository(authTokenDao)

        enableEdgeToEdge()
        setContent {
            CoLabTasksAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    navegationApp( authTokenRepository = authTokenRepository)
                }
            }
        }
    }
}

@Composable
fun navegationApp(authTokenRepository: AuthTokenRepository) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(authTokenRepository = authTokenRepository)
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoLabTasksAppTheme {
        Greeting("Android")
    }
}*/