package Debt

import FinancialGoals.Goal
import JsonUpdateable
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.util.*

class DebtMain(
    initDebtMap: MutableMap<String, Debt>,
    filePath: String,
    jsonHeading: String
) : JsonUpdateable(filePath, jsonHeading) {
    var debtMap: MutableMap<String, Debt>

    fun addDebt(
        debtName : String,
        debtAmount : Int,
        dueDate : Date,
        owedTo : DebtOwner,
        debtEntries: MutableList<Pair<Date, Int>>
    ) {
        debtMap.put(
            debtName,
            Debt(
                debtNameInit = debtName,
                debtAmountInit = debtAmount,
                debtDateInit = dueDate,
                debtOwnerInit = owedTo,
                entriesInit = debtEntries
            )
        )
    }

    fun modfiyDebt(debtName: String, amount: Int) {
        if (debtMap.contains(debtName)) {
            var modDebt: Debt = debtMap.get(debtName)!!
            modDebt.debtAmount = amount
        } else {
            throw Exception("That was not a valid debtName")
        }
    }

    fun modifyDebt(debtName: String, newDate: Date) {
        if (debtMap.contains(debtName)) {
            var modDebt: Debt = debtMap.get(debtName)!!
            modDebt.debtDate = newDate
        } else {
            throw Exception("That was not a valid debtName")
        }
    }

    fun modifyDebt(debtName: String, newEntry: Pair<Date, Int>) {
        if (debtMap.contains(debtName)) {
            var modDebt: Debt = debtMap.get(debtName)!!
            modDebt.entries.add(newEntry)
        } else {
            throw Exception("That was not a valid debtName")
        }
    }

    fun modifyDebt(debtName: String, newName: String) {
        if (debtMap.contains(debtName)) {
            var modDebt: Debt = debtMap.get(debtName)!!

            var savedAmount: Int = modDebt.debtAmount
            var savedDate: Date = modDebt.debtDate
            var savedEntries: MutableList<Pair<Date, Int>> = modDebt.entries

            debtMap.remove(debtName)
            debtMap.put(
                newName, Debt(
                    debtNameInit = newName,
                    debtAmountInit = savedAmount,
                    debtDateInit = savedDate,
                    entriesInit = savedEntries
                )
            )
        } else {
            throw Exception("That was not a valid debtName")
        }
    }

    fun modifyDebt(debtName : String, newDebtOwner :DebtOwner) {
        if(debtMap.contains(debtName)) {
            var modDebt : Debt = debtMap.get(debtName)!!
            modDebt.owedTo = newDebtOwner
        } else {
            throw Exception("That was not a valid newDebtOwner or debtName")
        }
    }

    override fun updateJson(): Boolean {
        specRoot.asJsonObject.get("debts").asJsonArray.apply {
            for(index in 0..(this.size() - 1))
                this.remove(0)
        }

        var valueMap : List<Debt> = debtMap.values.toList()
        for(element in valueMap) {
            var inputObject = JsonObject()
            inputObject.addProperty("name", element.debtName)
            inputObject.addProperty("amount", element.debtAmount)
            inputObject.addProperty("date", element.debtDate.toString())
            inputObject.add("debtOwner", JsonObject().apply{
                this.addProperty("name", element.owedTo.name)
                this.addProperty("amount", element.owedTo.contactInfo)
            })
            inputObject.add("entries", JsonArray().apply {
                for(entry in element.entries) {
                    var entryObject : JsonObject = JsonObject()
                    entryObject.addProperty("date", entry.first.toString())
                    entryObject.addProperty("amount", entry.second)
                }
            })

            specRoot.asJsonObject.get("debts").asJsonArray.add(inputObject)
        }

        return true
    }

    init {
        debtMap = initDebtMap
    }
}