package com.vardhanharsh.mywallet.screens


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vardhanharsh.mywallet.components.TableRow
import com.vardhanharsh.mywallet.components.UnstyledTextField
import com.vardhanharsh.mywallet.ui.theme.BackgroundElevated
import com.vardhanharsh.mywallet.ui.theme.DividerColor
import com.vardhanharsh.mywallet.ui.theme.MyWalletTheme
import com.vardhanharsh.mywallet.ui.theme.Shapes
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground

// helps with "by remember" code
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.vardhanharsh.mywallet.models.Recurrence
import com.vardhanharsh.mywallet.ui.theme.Primary
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.viewmodels.AddViewModel


// one opt in is for Scaffold and another is for date-picker
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Add(navController: NavController, vm: AddViewModel = viewModel()) {
// vm = viewModel
    val state by vm.uiState.collectAsState()

    val recurrences = listOf(
        Recurrence.None,
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )


    val categories = listOf<String>(
        "Groceries",
        "Bills",
        "Travel",
        "Grooming",
        "Clothes"
    )




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

            Column(
                modifier = Modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Order of modifier settings matter. Everything is build layer over layer.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(shape = Shapes.large)
                        .background(BackgroundElevated)
                ) {

                    TableRow(text = "Amount") {
                        UnstyledTextField(
                            value = state.amount,
                            // :: provides reference to that function
                            onValueChange = vm::setAmount,
                            placeholder = { Text(text = "0") },
                            arrangement = Arrangement.End,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.End,
                                fontSize = 17.sp

                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Recurrence") {

                        var menuOpened by remember {
                            mutableStateOf(false)
                        }

                        TextButton(onClick = { menuOpened = true }) {

                            Text(state.recurrence?.name ?: Recurrence.None.name)
                            DropdownMenu(expanded = menuOpened, onDismissRequest = { /*TODO*/ }) {

                                recurrences.forEach { recurrence ->
                                    DropdownMenuItem(text = { Text(text = recurrence.name) },
                                        onClick = {
                                            vm.setRecurrence(recurrence)
                                            menuOpened = false
                                        })
                                }


                            }
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)

                    var datePickerShow by remember{
                        mutableStateOf(false)
                    }
                    TableRow(text = "Date"){
                        TextButton(onClick = { datePickerShow = true }) {
                            Text(state.date.toString())
                        }

                        if(datePickerShow){
                            // this date picker is from third party library. See gradle:app
                            DatePickerDialog(
                                onDismissRequest = { datePickerShow = false },
                                onDateChange = {
                                    vm.setDate(it)
                                    datePickerShow = false
                                },
                                initialDate = state.date,
                                title = {Text("Select Date", style = Typography.titleMedium)}

                            )
                        }





                    }

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Note", content = {
                        UnstyledTextField(
                            value = state.note,
                            onValueChange = vm::setNote,
                            placeholder = {
                                Text(
                                    text = "Yo type here",
                                    modifier = Modifier.padding(start = 8.dp, top = 3.dp)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.End,
                                fontSize = 17.sp
                            )
                        )
                    })

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Category"){

                        var CategoriesMenuOpened by remember {
                            mutableStateOf(false)
                        }

                        TextButton(onClick = { CategoriesMenuOpened = true }) {
                            // TODO: change the color of text based on selected category
                            Text(text = state.category?: "Select a category first")
                            DropdownMenu(expanded = CategoriesMenuOpened, onDismissRequest = { /*TODO*/ }) {

                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(start = 2.dp, end = 12.dp)
                                                        .size(12.dp)
                                                        .background(Primary, shape = CircleShape),
                                                )
                                                Text(text = category)
                                            }

                                               },
                                        onClick = {
                                            vm.setCategory(category)
                                            CategoriesMenuOpened = false
                                        })
                                }


                            }
                        }
                    }
                }


                Button(
                    onClick = vm::submitExpense,
                    modifier = Modifier.padding(16.dp),
                    shape = Shapes.large
                ) {
                    Text(text = "Submit Expense")
                }

            }

        }

    )

}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAdd() {

    MyWalletTheme {
        val navController = rememberNavController()
        Add(navController)
    }


}