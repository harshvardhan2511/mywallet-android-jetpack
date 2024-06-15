package com.vardhanharsh.mywallet.models


// Why sealed class: -Representing state, - exhaustive when statements, - closed hierarchies (no class can extend)
// Use sealed class where exact set of subclasses are known and limited
// Best for modelling states and results
sealed class Recurrence(val name: String, val target: String) {
    object None : Recurrence("None", "None")
    object Daily : Recurrence("Daily", "Today")
    object Weekly : Recurrence("Weekly", "This week")
    object Monthly : Recurrence("Monthly", "This month")
    object Yearly : Recurrence("Yearly", "This year")
}

fun String.toRecurrence(): Recurrence {
    return when(this) {
        "None" -> Recurrence.None
        "Daily" -> Recurrence.Daily
        "Weekly" -> Recurrence.Weekly
        "Monthly" -> Recurrence.Monthly
        "Yearly" -> Recurrence.Yearly
        else -> Recurrence.None
    }
}
