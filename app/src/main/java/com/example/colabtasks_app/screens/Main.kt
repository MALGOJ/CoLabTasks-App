package com.example.colabtasks_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import com.example.colabtasks_app.ui.theme.CoLabTasksAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    authTokenRepository: AuthTokenRepository,
    navController: NavHostController
) {
    var menuExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    CoLabTasksAppTheme {
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
                    // Contenido principal
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Bienvenidos a la app CoLabTasks-App",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Lleva agenda de tus tareas y proyectos",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 18.sp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }

                    if (menuExpanded) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f)) // Fondo semitransparente
                                .zIndex(1f) // Asegura que el fondo esté por encima del contenido principal
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(250.dp) // Ajusta el ancho del sidebar
                                .background(MaterialTheme.colorScheme.surface)
                                .zIndex(2f) // Asegura que el sidebar esté por encima del fondo semitransparente
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(bottom = 16.dp) // Añade un padding inferior para el botón
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
                                    .align(Alignment.BottomCenter) // Alinea el botón al final del elemento padre
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
                                        text = "Cerrar sección",
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
}

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenidos a la app CoLabTasks-App",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Lleva agenda de tus tareas y proyectos",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )
    }
}