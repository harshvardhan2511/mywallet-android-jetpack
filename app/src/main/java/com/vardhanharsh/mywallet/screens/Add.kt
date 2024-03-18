package com.vardhanharsh.mywallet.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vardhanharsh.mywallet.Greeting

@Composable
fun Add(navController: NavController, innerPadding: PaddingValues){

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("ADD")
    }

}