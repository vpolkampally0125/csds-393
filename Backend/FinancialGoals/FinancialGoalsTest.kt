package FinancialGoals

import Budgeting.BudgetEntry
import Budgeting.BudgetParent
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class FinancialGoalsTest {

    @Test
    fun addGoal() {
        val myDate = Date()
        val myList = mutableListOf<Pair<Date, Int>>()
        val financialGoal = FinancialGoals(mutableMapOf(),"/Users/eduardobautista/Downloads/outputfile-2.json","income")
        assertEquals(true, financialGoal.addGoal("payoff student loans",15000,myDate, myList))
    }

    @Test
    fun modfiyGoalAmount() {
        val myDate = Date()
        val myList = mutableListOf<Pair<Date, Int>>()
        val myMap =mutableMapOf<String, Goal>()
        myMap["Pay off car loans"] = Goal()
        val financialGoal = FinancialGoals(myMap,"/Users/eduardobautista/Downloads/outputfile-2.json","income")
        assertEquals(true, financialGoal.modifyGoalNewEntry("Pay off car loans",Pair(myDate,5)))
    }

    @Test
    fun modifyGoalDate() {
        val myDate = Date()
        val myList = mutableListOf<Pair<Date, Int>>()
        val myMap =mutableMapOf<String, Goal>()
        myMap["Pay off car loans"] = Goal()
        val financialGoal = FinancialGoals(myMap,"/Users/eduardobautista/Downloads/outputfile-2.json","income")
        assertEquals(true, financialGoal.modifyGoalDate("Pay off car loans",myDate))
    }

    @Test
    fun modifyGoalNewEntry() {
        val myDate = Date()
        val myList = mutableListOf<Pair<Date, Int>>()
        val myMap =mutableMapOf<String, Goal>()
        myMap["Pay off car loans"] = Goal()
        val financialGoal = FinancialGoals(myMap,"/Users/eduardobautista/Downloads/outputfile-2.json","income")
        assertEquals(true, financialGoal.modifyGoalNewEntry("Pay off car loans", Pair(myDate,5)))

    }

    @Test
    fun modifyGoalNewName() {
        val myDate = Date()
        val myList = mutableListOf<Pair<Date, Int>>()
        val myMap =mutableMapOf<String, Goal>()
        myMap["Pay off car loans"] = Goal()
        val financialGoal = FinancialGoals(myMap,"/Users/eduardobautista/Downloads/outputfile-2.json","income")
        assertEquals(true, financialGoal.modifyGoalNewName("Pay off car loans","My New Goal"))
    }
}