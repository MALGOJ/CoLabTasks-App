package com.example.colabtasks_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.colabtasks_app.DB.CoLabTasksDataBase
import com.example.colabtasks_app.DB.Dao.AuthTokenDao
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import com.example.colabtasks_app.screens.*
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
                    navegationApp(authTokenRepository = authTokenRepository)
                }
            }
        }
    }
}

@Composable
fun navegationApp(authTokenRepository: AuthTokenRepository) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf("login") }

    LaunchedEffect(Unit) {
        startDestination = if (authTokenRepository.count() == 0) "login" else "Menu"
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(
                authTokenRepository = authTokenRepository,
                navController = navController
            )
        }

        composable("Menu") {
            MainScreen(
                authTokenRepository = authTokenRepository,
                navController = navController
            )
        }

        composable("SignUp") {
            SignUpScreen(navController = navController)
        }

        composable("TasksList") {
            TasksList(
                authTokenRepository = authTokenRepository,
                navController = navController
            )
        }
        composable("TaskCreate") {
            TaskCreate(
                authTokenRepository = authTokenRepository,
                navController = navController
            )
        }
        composable("TaskUpdate/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            TaskUpdate(
                taskId = taskId,
                authTokenRepository = authTokenRepository,
                navController = navController
            )
        }

    }
}
