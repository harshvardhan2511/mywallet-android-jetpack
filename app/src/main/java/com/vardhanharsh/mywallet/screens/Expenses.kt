package com.vardhanharsh.mywallet.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController){

    Scaffold(

        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Expenses")},
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = topAppBarBackground
                )
            )
        },

        content = { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Expenses")
            }

        }

    )



}