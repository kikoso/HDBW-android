package com.enrique.hdbwandroid.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WelcomeScreen(viewModel: MyViewModel) {
    Text("Hallo, ${viewModel.name}")
}