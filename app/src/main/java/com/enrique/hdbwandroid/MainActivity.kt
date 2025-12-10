package com.enrique.hdbwandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enrique.hdbwandroid.ui.TaskViewModel
import com.enrique.hdbwandroid.ui.TaskViewModelFactory
import com.enrique.hdbwandroid.ui.MainViewModel
import com.enrique.hdbwandroid.ui.MainViewModelFactory
import com.enrique.hdbwandroid.ui.theme.HDBWAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HDBWAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val app = context.applicationContext as HDBWApplication
                    val mainViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(app.userPreferences))
                    val name by mainViewModel.userName.collectAsState()

                    if (name == null) {
                        NameScreen(
                            modifier = Modifier.padding(innerPadding),
                            onNameEntered = { mainViewModel.saveUserName(it) }
                        )
                    } else {
                        TaskScreen(
                            modifier = Modifier.padding(innerPadding),
                            userName = name!!,
                            taskViewModel = viewModel(factory = TaskViewModelFactory(app.repository, name!!))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NameScreen(modifier: Modifier = Modifier, onNameEntered: (String) -> Unit) {
    var tempName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = tempName,
            onValueChange = { tempName = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onNameEntered(tempName) },
            modifier = Modifier.fillMaxWidth(),
            enabled = tempName.isNotBlank()
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun TaskScreen(modifier: Modifier = Modifier, userName: String, taskViewModel: TaskViewModel) {
    var taskDescription by remember { mutableStateOf("") }
    val tasks by taskViewModel.tasks.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome, $userName!")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { newValue ->
                taskDescription = newValue
            },
            label = { Text("Enter a new task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (taskDescription.isNotBlank()) {
                    taskViewModel.addTask(taskDescription)
                    taskDescription = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = task.description)
                    Button(onClick = { taskViewModel.deleteTask(task) }) {
                        Text("Delete")
                    }
                    Button(onClick = { taskViewModel.modifyTask(task) }) {
                        Text("Modify")
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NameScreenPreview() {
    HDBWAndroidTheme {
        NameScreen(onNameEntered = {})
    }
}