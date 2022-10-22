import Budgeting.BudgetEntry
import Budgeting.BudgetParent
import java.util.*

class BudgetingProjectMain(path : String) {
    var reader : JsonReader;
    var income : BudgetParent
    var expenses : BudgetParent
    var tags : MutableList<String>

    fun getIncome() : Int { return income.getTot() }

    fun addIncome(name : String, value : Int, tags : MutableList<String>) {
        var currDate : Date = Date()
        income.entries.add(BudgetEntry(name, currDate, value, tags))
    }

    fun getExpenses() : Int { return expenses.getTot() }

    fun addExpense(name : String, value : Int, tags : MutableList<String>) {
        var currDate : Date = Date()
        expenses.entries.add(BudgetEntry(name, currDate, value, tags))

    }

    init {
        reader = JsonReader(path)
        income = reader.income
        expenses = reader.expenses
        tags = reader.tags

        income.removePastNodes()
        expenses.removePastNodes()
    }
}

fun main() {
    var instance : BudgetingProjectMain = BudgetingProjectMain("/Users/eduardobautista/Downloads")
    println("Total Income: ${instance.income.totalValue}")
    println("Total Expenses : ${instance.expenses.totalValue}")
    println("Income List: ${instance.income.entries}")
    println("Expenses List: ${instance.expenses.entries}")
}