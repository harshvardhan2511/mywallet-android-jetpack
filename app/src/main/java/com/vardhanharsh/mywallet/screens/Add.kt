package com.vardhanharsh.mywallet.screens

import android.app.DatePickerDialog
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.icu.text.SimpleDateFormat
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import com.vardhanharsh.mywallet.ui.theme.Primary
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController) {
    val recurrences = listOf<String>(
        "None",
        "Daily",
        "Weekly",
        "Monthly",
        "Yearly"
    )

    var selectedRecurrence by remember {
        mutableStateOf(recurrences[0])
    }

    val categories = listOf<String>(
        "Groceries",
        "Bills",
        "Travel",
        "Grooming",
        "Clothes"
    )

    var selectedCategory by remember{
        mutableStateOf(categories[0])
    }

    // CODE FOR DATE PICKER STARTS

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val mContext = LocalContext.current

    val sdf = SimpleDateFormat("dd-MM-yyyy")
    val currentDate = sdf.format(Date())
    val date = remember {  mutableStateOf(currentDate)  }

    // DatePickerDialog is from "Android.app" class
    val datePickerDialog = DatePickerDialog(
        mContext,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
        }, mYear, mMonth, mDay
    )

    // CODE FOR DATE PICKER ENDS

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
                            value = "$0.00",
                            onValueChange = {},
                            placeholder = { Text(text = "Yo type here") },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(
                                textAlign = TextAlign.End,
                                fontSize = 17.sp

                            )
                        )
                    }

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Recurrence") {

                        var menuOpened by remember {
                            mutableStateOf(false)
                        }

                        TextButton(onClick = { menuOpened = true }) {

                            Text(text = selectedRecurrence)
                            DropdownMenu(expanded = menuOpened, onDismissRequest = { /*TODO*/ }) {

                                recurrences.forEach { recurrence ->
                                    DropdownMenuItem(text = { Text(text = recurrence) },
                                        onClick = {
                                            selectedRecurrence = recurrence
                                            menuOpened = false
                                        })
                                }


                            }
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Date"){
                        TextButton(onClick = { datePickerDialog.show() }) {
                            Text(text = date.value)
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                    TableRow(text = "Note", content = {
                        UnstyledTextField(
                            value = "",
                            onValueChange = {},
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

                            Text(text = selectedCategory)
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
                                            selectedCategory = category
                                            CategoriesMenuOpened = false
                                        })
                                }


                            }
                        }
                    }
                }


                Button(
                    onClick = { /*TODO*/ },
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