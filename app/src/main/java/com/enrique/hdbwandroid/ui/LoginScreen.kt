package com.enrique.hdbwandroid.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.enrique.hdbwandroid.NameScreen
import com.enrique.hdbwandroid.ui.theme.HDBWAndroidTheme

@Composable
fun LoginScreen() {
    var input by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = input,
            onValueChange = {
                input = it
                error = it.isBlank()
            },
            isError = error,
            label = { Text("Eingabe") },
            modifier = Modifier.testTag("inputField")
        )
        if (error) {
            Text("Fehler: Eingabe darf nicht leer sein", color = Color.Red)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    HDBWAndroidTheme {
        LoginScreen()
    }
}