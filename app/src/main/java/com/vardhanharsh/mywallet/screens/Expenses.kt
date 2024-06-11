package com.vardhanharsh.mywallet.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vardhanharsh.mywallet.components.PickerTrigger
import com.vardhanharsh.mywallet.components.expensesList.ExpensesList
import com.vardhanharsh.mywallet.mock.mockExpenses
import com.vardhanharsh.mywallet.models.Recurrence
import com.vardhanharsh.mywallet.ui.theme.LabelSecondary
import com.vardhanharsh.mywallet.ui.theme.MyWalletTheme
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground
import com.vardhanharsh.mywallet.viewmodels.ExpensesViewModel
import java.text.DecimalFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Expenses(
    navController: NavController,
    vm: ExpensesViewModel = viewModel()
) {
    val recurrences = listOf(
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )

    val state by vm.uiState.collectAsState()
    var recurrenceMenuOpened by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Expenses") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = topAppBarBackground
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Total for:",
                        style = Typography.bodyMedium,
                    )

                    // TimeFrame Picker

                    PickerTrigger(
                        state.recurrence.target ?: Recurrence.None.target,
                        onClick = { recurrenceMenuOpened = !recurrenceMenuOpened },
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    
                    DropdownMenu(expanded = recurrenceMenuOpened,
                        onDismissRequest = { recurrenceMenuOpened = false }) {
                        recurrences.forEach { recurrence ->
                            DropdownMenuItem(text = { Text(recurrence.target) }, onClick = {
                                vm.setRecurrence(recurrence)
                                recurrenceMenuOpened = false
                            })
                        }
                    }


                }
                Row(modifier = Modifier.padding(vertical = 32.dp)) {
                    Text(
                        "$",
                        style = Typography.bodyMedium,
                        color = LabelSecondary,
                        modifier = Modifier.padding(end = 4.dp, top = 4.dp)
                    )
                    Text(
                        DecimalFormat("0.#").format(state.sumTotal),
                        style = Typography.titleLarge
                    )
                }
                ExpensesList(
                    expenses = mockExpenses,
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                )
            }
        }
    )
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ExpensesPreview() {
    MyWalletTheme {
        Expenses(navController = rememberNavController())
    }
}