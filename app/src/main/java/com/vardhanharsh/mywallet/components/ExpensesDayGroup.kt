package com.vardhanharsh.mywallet.components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vardhanharsh.mywallet.models.DayExpenses
import com.vardhanharsh.mywallet.ui.theme.LabelSecondary
import com.vardhanharsh.mywallet.ui.theme.Typography
import com.vardhanharsh.mywallet.utilities.formatDay
import java.text.DecimalFormat
import java.time.LocalDate

@Composable
fun ExpensesDayGroup(
    date: LocalDate,
    dayExpenses: DayExpenses,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            date.formatDay(),
            style = Typography.headlineMedium,
            color = LabelSecondary
        )
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))
        dayExpenses.expenses.forEach { expense ->
            ExpenseRow(
                expense = expense,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total:", style = Typography.bodyMedium, color = LabelSecondary)
            Text(
                DecimalFormat("USD 0.#").format(dayExpenses.total),
                style = Typography.headlineMedium,
                color = LabelSecondary
            )
        }
    }
}