package com.vardhanharsh.mywallet.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vardhanharsh.mywallet.components.TableRow
import com.vardhanharsh.mywallet.components.UnstyledTextField
import com.vardhanharsh.mywallet.ui.theme.BackgroundElevated
import com.vardhanharsh.mywallet.ui.theme.DividerColor
import com.vardhanharsh.mywallet.ui.theme.Shapes
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController){

    Scaffold(

        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Add") },
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
                    .clip(shape = Shapes.large)
                    .background(BackgroundElevated)
                ) {
                    TableRow(text = "Amount"){
                        UnstyledTextField(
                            value = "$0.00",
                            onValueChange = {},
                            placeholder = {Text(text = "Yo type here")},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.End,
                                fontSize = 17.sp

                            )
                        )
                    }
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Recurrence")
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Date")
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Note", content = {
                        UnstyledTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = {Text(text = "Yo type here", modifier = Modifier.padding(start = 8.dp, top = 3.dp))},
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.End,
                                fontSize = 17.sp
                            )
                        )
                    })
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Category")
                }
            }

        }

    )

}