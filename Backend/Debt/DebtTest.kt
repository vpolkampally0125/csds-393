package Debt

import Debt.Debt
import Debt.DebtOwner

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class DebtTest {

    @Test
    fun addDebt() {
        val myDate = Date()

        val debtMap1 : MutableMap<String, Debt> = mutableMapOf()
        debtMap1.put("Tester1", Debt("Tester1", 0, myDate))
        val debtMain1 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")

        var debtMap2 : MutableMap<String, Debt> = mutableMapOf()
        val debtMain2 = DebtMain(debtMap2,"./src/main/resources/outputfile.json","debts")
        debtMain2.addDebt("Tester1", 0, myDate, DebtOwner("Unnamed Lender", ""), mutableListOf())

        assertEquals(debtMain1.debtMap.keys.toList().get(0), debtMain2.debtMap.keys.toList().get(0))
    }

    @Test
    fun modifyDebtAmount() {
        val myDate = Date()

        val debtMap1 : MutableMap<String, Debt> = mutableMapOf()
        debtMap1.put("Tester1", Debt("Tester1", 10, myDate))
        val debtMain1 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")

        val debtMap2 : MutableMap<String, Debt> = mutableMapOf()
        debtMap2.put("Tester1", Debt("Tester1", 0, myDate))
        val debtMain2 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")
        debtMain2.modifyDebt(debtName = "Tester1", amount = 10)

        assertEquals(debtMain1.debtMap.get("Tester1")!!.debtAmount, debtMain2.debtMap.get("Tester1")!!.debtAmount)
    }

    @Test
    fun modifyDebtAmountDate() {
        val myDate = Date()
        val changeDate = Date(1000L)

        val debtMap1 : MutableMap<String, Debt> = mutableMapOf()
        debtMap1.put("Tester1", Debt("Tester1", 10, changeDate))
        val debtMain1 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")

        val debtMap2 : MutableMap<String, Debt> = mutableMapOf()
        debtMap2.put("Tester1", Debt("Tester1", 0, myDate))
        val debtMain2 = DebtMain(debtMap2,"./src/main/resources/outputfile.json","debts")
        debtMain2.modifyDebt(debtName = "Tester1", newDate = changeDate)

        println("MAIN1: ${debtMain1.debtMap.get("Tester1")!!.debtDate}, Main2: ${debtMain2.debtMap.get("Tester1")!!.debtDate}")
        assertEquals(debtMain1.debtMap.get("Tester1")!!.debtDate, debtMain2.debtMap.get("Tester1")!!.debtDate)
    }

    @Test
    fun modifyDebtEntry() {
        val myDate = Date()
        val changeDate = Date(1000L)

        val debtMap1 : MutableMap<String, Debt> = mutableMapOf()
        debtMap1.put("Tester1", Debt("Tester1", 10, changeDate))
        val debtMain1 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts",)

        val debtMap2 : MutableMap<String, Debt> = mutableMapOf()
        debtMap2.put("Tester1", Debt("Tester1", 0, myDate))
        val debtMain2 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")
        debtMain2.modifyDebt(debtName = "Tester1", newEntry = Pair(changeDate, 10))

        assertEquals(debtMain1.debtMap.get("Tester1")!!.entries, debtMain2.debtMap.get("Tester1")!!.entries)
    }

    @Test
    fun modifyDebtName() {
        val myDate = Date()

        val debtMap1 : MutableMap<String, Debt> = mutableMapOf()
        debtMap1.put("Tester2", Debt("Tester2", 10, myDate))
        val debtMain1 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")

        val debtMap2 : MutableMap<String, Debt> = mutableMapOf()
        debtMap2.put("Tester1", Debt("Tester1", 0, myDate))
        val debtMain2 = DebtMain(debtMap2,"./src/main/resources/outputfile.json","debts")
        debtMain2.modifyDebt(debtName = "Tester1", newName = "Tester2")
        assertEquals(debtMain1.debtMap.get("Tester2")!!.debtName, debtMain2.debtMap.get("Tester2")!!.debtName)
    }

    @Test
    fun modifyDebtOwner() {
        val myDate = Date()

        val debtMap1 : MutableMap<String, Debt> = mutableMapOf()
        debtMap1.put("Tester1", Debt("Tester1", 10, myDate, DebtOwner("Tester2", "Temp2")))
        val debtMain1 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")

        val debtMap2 : MutableMap<String, Debt> = mutableMapOf()
        debtMap2.put("Tester1", Debt("Tester1", 0, myDate, DebtOwner("Tester1", "Temp1")))
        val debtMain2 = DebtMain(debtMap1,"./src/main/resources/outputfile.json","debts")
        debtMain2.modifyDebt(debtName = "Tester1", newDebtOwner = DebtOwner("Tester2", "Temp2"))

        assertEquals(debtMain1.debtMap.get("Tester1")!!.owedTo, debtMain2.debtMap.get("Tester1")!!.owedTo)
    }

}