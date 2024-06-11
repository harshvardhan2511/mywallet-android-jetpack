package com.vardhanharsh.mywallet.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vardhanharsh.mywallet.R
import com.vardhanharsh.mywallet.components.charts.MonthlyChart
import com.vardhanharsh.mywallet.components.charts.WeeklyChart
import com.vardhanharsh.mywallet.components.charts.YearlyChart
import com.vardhanharsh.mywallet.components.expensesList.ExpensesList
import com.vardhanharsh.mywallet.mock.mockExpenses
import com.vardhanharsh.mywallet.models.Recurrence
import com.vardhanharsh.mywallet.ui.theme.LabelSecondary
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.ui.theme.topAppBarBackground
import com.vardhanharsh.mywallet.viewmodels.ReportsViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Report( vm: ReportsViewModel = viewModel()){

    val uiState = vm.uiState.collectAsState().value

    val recurrences = listOf(
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Reports") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = topAppBarBackground
                ),

                actions = {
                    IconButton(onClick = vm::openRecurrenceMenu) {
                        Icon(painterResource(id = R.drawable.today), contentDescription = "Change recurrence")
                    }
                    DropdownMenu(expanded = uiState.recurrenceMenuOpened,
                        onDismissRequest = vm::closeRecurrenceMenu) {
                        recurrences.forEach { recurrence ->
                            DropdownMenuItem(text = { Text(recurrence.name) }, onClick = {
                                vm.setRecurrence(recurrence)
                                vm.closeRecurrenceMenu()
                            })
                        }
                    }
                }

            )

        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("12 Sep - 18 Sep", style = Typography.titleSmall)
                        Row(modifier = Modifier.padding(top = 4.dp)) {
                            Text(
                                "USD",
                                style = Typography.bodyMedium,
                                color = LabelSecondary,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text("85", style = Typography.headlineMedium)
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Avg/day", style = Typography.titleSmall)
                        Row(modifier = Modifier.padding(top = 4.dp)) {
                            Text(
                                "USD",
                                style = Typography.bodyMedium,
                                color = LabelSecondary,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text("85", style = Typography.headlineMedium)
                        }
                    }
                }

                Box(modifier = Modifier
                    .height(180.dp)
                    .padding(vertical = 16.dp)) {
                    when (uiState.recurrence) {
                        Recurrence.Weekly -> WeeklyChart(expenses = mockExpenses)
                        Recurrence.Monthly -> MonthlyChart(expenses = mockExpenses, LocalDate.now())
                        Recurrence.Yearly -> YearlyChart(expenses = mockExpenses)
                        else -> Unit
                    }
                }

                ExpensesList(
                    expenses = mockExpenses, modifier = Modifier
                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                )
            }
        }
    )

}