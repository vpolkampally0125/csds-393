package FinancialGoals

import java.util.*

class Goal(
    goalNameInit : String = "Unnamed Goal",
    goalAmountInit : Int = 0,
    goalDateInit : Date = Date(),
    entriesInit : MutableList<Pair<Date, Int>> = mutableListOf()
) {
    var goalName : String
    var goalAmount : Int
    var goalDate : Date
    var entries : MutableList<Pair<Date, Int>>

    init {
        goalName = goalNameInit
        goalAmount = goalAmountInit
        goalDate = goalDateInit
        entries = entriesInit
    }
}