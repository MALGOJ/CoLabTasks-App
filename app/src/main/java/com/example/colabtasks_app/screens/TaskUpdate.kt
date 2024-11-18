package com.example.colabtasks_app.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.colabtasks_app.Api.CreateTask
import com.example.colabtasks_app.Api.RetrofitInstance
import com.example.colabtasks_app.DB.Repository.AuthTokenRepository
import com.example.colabtasks_app.Data.TaskPriority
import com.example.colabtasks_app.Data.TaskStatus
import com.example.colabtasks_app.screens.Scaffold.AppScaffold
import kotlinx.coroutines.launch
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskUpdate(
    authTokenRepository: AuthTokenRepository,
    navController: NavHostController,
    taskId: String?
){
    println("datos task update $taskId")
    val scope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(TaskStatus.TODO) }
    var priority by remember { mutableStateOf(TaskPriority.LOW) }
    var expandedStatus by remember { mutableStateOf(false) }
    var expandedPriority by remember { mutableStateOf(false) }
    var dueDate by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dueDate = "$year-${month + 1}-$dayOfMonth"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, minute: Int ->
            dueDate += "T$hour:$minute:00"
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    AppScaffold(
        authTokenRepository = authTokenRepository,
        navController = navController,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expandedStatus,
                onExpandedChange = { expandedStatus = !expandedStatus }
            ) {
                TextField(
                    value = status.key,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Estado") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedStatus,
                    onDismissRequest = { expandedStatus = false }
                ) {
                    TaskStatus.values().forEach { taskStatus ->
                        DropdownMenuItem(
                            text = { Text(taskStatus.key) },
                            onClick = {
                                status = taskStatus
                                expandedStatus = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expandedPriority,
                onExpandedChange = { expandedPriority = !expandedPriority }
            ) {
                TextField(
                    value = priority.key,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Prioridad") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPriority)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedPriority,
                    onDismissRequest = { expandedPriority = false }
                ) {
                    TaskPriority.values().forEach { taskPriority ->
                        DropdownMenuItem(
                            text = { Text(taskPriority.key) },
                            onClick = {
                                priority = taskPriority
                                expandedPriority = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { datePickerDialog.show() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Selecccione Fecha")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { timePickerDialog.show() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Selecccione Hora")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Fecha seleccionada : $dueDate")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    scope.launch {
                        if (title.isNotEmpty() && description.isNotEmpty() && dueDate.isNotEmpty()) {
                            val newTask = CreateTask(
                                titleDto = title,
                                descriptionDto = description,
                                statusDto = status.name,
                                priorityDto = priority.name,
                                dueDateDto = dueDate
                            )
                            val toquen = authTokenRepository.getAuthToken(idToken = 1)

                            val response = RetrofitInstance.api.saveTask(
                                token = "Bearer ${toquen?.token ?: ""}",
                                task = newTask
                            )
                            if (response.isSuccessful) {
                                navController.navigate("TasksList")
                            } else if (response.code() == 403) {
                                println("Token de seguridad expiró")
                                snackbarHostState.showSnackbar(
                                    message = "Sección ha expirado, por favor inicie sesión nuevamente"
                                )
                                navController.navigate("login")
                            } else {
                                snackbarHostState.showSnackbar(
                                    message = "Error al guardar la tarea"
                                )
                            }
                        } else {
                            snackbarHostState.showSnackbar(
                                message = "Por favor complete todos los campos"
                            )
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Actualizar")
            }
        }
    }
}