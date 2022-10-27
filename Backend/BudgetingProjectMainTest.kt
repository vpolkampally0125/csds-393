import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BudgetingProjectMainTest {

    @Test
    fun getIncome() {
        val budgetingProjectMain = BudgetingProjectMain("/Users/eduardobautista/Downloads/outputfile.json")
        assertEquals(100, budgetingProjectMain.getIncome())

    }

    @Test
    fun getExpenses() {
        val budgetingProjectMain = BudgetingProjectMain("/Users/eduardobautista/Downloads/outputfile.json")
        assertEquals(0, budgetingProjectMain.getExpenses())
    }

}