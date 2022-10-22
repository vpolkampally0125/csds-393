package Budgeting

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class BudgetParentTest {

    @Test
    fun getTot() {
        val budgetParent = BudgetParent(14, PriorityQueue())
        assertEquals(14,budgetParent.getTot())
    }

    @Test
    fun getCurrentDate() {
        val budgetParent = BudgetParent(76,PriorityQueue())
    }

    @Test
    fun tagEntry() {
        val budgetParent = BudgetParent(78,PriorityQueue())
        assertEquals(mutableListOf("Food"), budgetParent.tagEntry(BudgetEntry("Chipotle", Date(),8, mutableListOf()),"Food"))
    }
}