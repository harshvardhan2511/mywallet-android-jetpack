package com.vardhanharsh.mywallet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vardhanharsh.mywallet.components.TableRow
import com.vardhanharsh.mywallet.ui.theme.BackgroundElevated
import com.vardhanharsh.mywallet.ui.theme.Shapes
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController){

    Scaffold(

        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Settings") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = topAppBarBackground
                )
            )
        },

        content = { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {
                // Order of modifier settings matter. Everything is build layer over layer.
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(shape = Shapes.medium)
                    .background(BackgroundElevated)
                ) {
                    TableRow(text = "Categories", hasArrow = true)
                    Divider(modifier = Modifier.height(2.dp))
                    TableRow(text = "Erase all data", isDestructive = true)
                }
            }

        }

    )

}