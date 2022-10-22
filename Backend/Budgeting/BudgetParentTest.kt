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
}