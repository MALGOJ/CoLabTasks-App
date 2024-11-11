package com.example.colabtasks_app.screens.Scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    authTokenRepository: AuthTokenRepository,
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CoLabTasks-App") },
                navigationIcon = {
                    IconButton(onClick = { menuExpanded = !menuExpanded }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu Icon")
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                content(paddingValues)

                if (menuExpanded) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .zIndex(1f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(250.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .zIndex(2f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(bottom = 16.dp)
                        ) {
                            Button(
                                onClick = {  },
                                modifier = Modifier
                                    .padding(vertical = 1.dp)
                                    .fillMaxWidth(),
                                shape = RectangleShape
                            ) {
                                Text(
                                    text = "Proyectos",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Button(
                                onClick = {
                                    navController.navigate("ListTanks")
                                },
                                modifier = Modifier
                                    .padding(vertical = 1.dp)
                                    .fillMaxWidth(),
                                shape = RectangleShape
                            ) {
                                Text(
                                    text = "Tareas",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                        ) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        authTokenRepository.deleteAll()
                                        navController.navigate("login")
                                    }
                                },
                                modifier = Modifier
                                    .padding(vertical = 1.dp)
                                    .fillMaxWidth(),
                                shape = RectangleShape
                            ) {
                                Text(
                                    text = "Cerrar sesión",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}