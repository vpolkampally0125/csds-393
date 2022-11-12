package Budgeting

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class BudgetParentTest {

    @Test
    fun removeValue() {
        val myDate = Date()
        val budgetEntry = BudgetEntry("McDonalds", myDate, 4, mutableListOf("Food"))
        val thisQueue: PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>()
        val budgetParent = BudgetParent(12, thisQueue,"/Users/eduardobautista/Downloads/outputfile-2.json","expenses")
        assertEquals(true, budgetParent.removeValue(budgetEntry))
    }

    @Test
    fun tagEntry() {
        val myDate = Date()
        val budgetEntry = BudgetEntry("BurgerKing", myDate, 4, mutableListOf("Food"))
        val thisQueue: PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>()
        val budgetParent = BudgetParent(12, thisQueue,"/Users/eduardobautista/Downloads/outputfile-2.json","expenses")
        assertEquals("Food", budgetParent.tagEntry(budgetEntry,"Food"))
    }

    @Test
    fun removeTagFromEntry() {
        val myDate = Date()
        val budgetEntry = BudgetEntry("McDonalds", myDate, 4, mutableListOf("Food"))
        val thisQueue: PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>()
        val budgetParent = BudgetParent(12, thisQueue,"/Users/eduardobautista/Downloads/outputfile-2.json","expenses")
        assertEquals(true, budgetParent.removeTagFromEntry(budgetEntry,"Food"))
    }

    @Test
    fun updateTotalVal() {
        val myDate = Date()
        val budgetEntry = BudgetEntry("Wendys", myDate, 4, mutableListOf("Food"))
        val thisQueue: PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>()
        val budgetParent = BudgetParent(12, thisQueue,"/Users/eduardobautista/Downloads/outputfile-2.json","expenses")
        assertEquals(true, budgetParent.updateTotalVal())
    }

    @Test
    fun updateJson() {
        val myDate = Date()
        val budgetEntry = BudgetEntry("Chipotle", myDate, 17, mutableListOf("Food"))
        val thisQueue: PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>()
        val budgetParent = BudgetParent(12, thisQueue,"/Users/eduardobautista/Downloads/outputfile-2.json","expenses")
        assertEquals(true, budgetParent.updateJson())

    }
}