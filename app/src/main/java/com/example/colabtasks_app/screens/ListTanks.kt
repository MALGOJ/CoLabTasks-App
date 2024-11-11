package com.example.colabtasks_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.colabtasks_app.Api.RetrofitInstance
import com.example.colabtasks_app.Api.Tasks
import com.example.colabtasks_app.DB.Entity.AuthToken
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import kotlinx.coroutines.launch

@Composable
fun ListTaks(authTokenRepository: AuthTokenRepository, navController: NavHostController) {
    println(":::::::ESTA ACA :::::::::")
    val scope = rememberCoroutineScope()
    var token by remember { mutableStateOf<AuthToken?>(null) }
    var tasks by remember { mutableStateOf<List<Tasks>>(emptyList()) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        scope.launch {
            val authToken = authTokenRepository.getAuthToken(idToken = 1)
            token = authToken
            println("Toquen ${token}")
            val response = RetrofitInstance.api.getTasks("Bearer ${token?.token ?: ""}")
            println(":::::::ESTA ACA ::::::::: ${response}")
            if (response.isSuccessful) {
                tasks = response.body() ?: emptyList()
            } else if (response.code() == 403) {
                snackbarHostState.showSnackbar("Token de seguridad expiró")
                navController.navigate("login")
            } else {
                snackbarHostState.showSnackbar("Ha ocurrido un error inesperado, intente después")
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(task: Tasks) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title ?: "", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = task.description ?: "", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Status: ${task.status}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Priority: ${task.priority}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Due Date: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Updated Date: ${task.updatedDate}", style = MaterialTheme.typography.bodySmall)
        }
    }
}