package com.example.colabtasks_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.colabtasks_app.Api.LoginRequest
import com.example.colabtasks_app.Api.RetrofitInstance
import com.example.colabtasks_app.DB.Entity.AuthToken
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun LoginScreen(
    authTokenRepository: AuthTokenRepository,
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Iniciar sesión",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        onLogin(email, password) {
                            if (it != null) {
                                val token = it
                                val tokenEntity = AuthToken(idToken = 1, token = token, email = email)
                                scope.launch {
                                    val cantidadRegistros = authTokenRepository.count()
                                    if (cantidadRegistros > 0) {
                                        authTokenRepository.deleteAll()
                                    }
                                    authTokenRepository.insertAuthToken(tokenEntity)
                                }
                                navController.navigate("Menu")
                            } else {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Credenciales incorrectas")
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Login", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = {
                    navController.navigate("SignUp")
                }
            ) {
                Text("¿No tienes una cuenta? Regístrate aquí")
            }
        }
    }
}

fun onLogin(email: String, password: String, onResult: (String?) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val body = LoginRequest(email, password)
            val response = RetrofitInstance.api.login(body)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val authHeader = response.headers()["Authorization"]
                    onResult(authHeader)
                } else {
                    onResult(null)
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onResult(null)
            }
        }
    }
}