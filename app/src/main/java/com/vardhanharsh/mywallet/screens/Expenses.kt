package com.vardhanharsh.mywallet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController = rememberNavController()){

    Scaffold(

        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Yellow
                ),
                // modifier = Modifier.fillMaxHeight(),
                title = { Text(text = "Expense")}

            )
        },

        content = { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {
                Text(text = "Expenses")
            }

        }

    )



}