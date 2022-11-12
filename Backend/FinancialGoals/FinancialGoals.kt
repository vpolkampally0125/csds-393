package FinancialGoals

import JsonUpdateable
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.util.*

class FinancialGoals(
    initGoalMap : MutableMap<String, Goal> = mutableMapOf(),
    filePath : String,
    jsonHeading : String
) : JsonUpdateable(filePath, jsonHeading) {
    var goalMap : MutableMap<String, Goal>

    fun addGoal(goalName : String, goalAmount : Int, goalDate : Date, goalEntries : MutableList<Pair<Date, Int>>): Boolean {
        goalMap.put(
            goalName, Goal(
                goalNameInit = goalName,
                goalAmountInit = goalAmount,
                goalDateInit = goalDate,
                entriesInit = goalEntries
            )
        )
        return true
    }

    fun modfiyGoalAmount(goalName : String, amount : Int): Boolean {
        if(goalMap.contains(goalName)) {
            var modGoal: Goal = goalMap.get(goalName)!!
            modGoal.goalAmount = amount
            return true
        }
        else {return false}
    }

    fun modifyGoalDate(goalName : String, newDate : Date): Boolean {
        if(goalMap.contains(goalName)) {
            var modGoal: Goal = goalMap.get(goalName)!!
            modGoal.goalDate = newDate
            return true
        }
        else {
        return false
        }
    }

    fun modifyGoalNewEntry(goalName : String, newEntry : Pair<Date, Int>): Boolean {
        if(goalMap.contains(goalName)) {
            var modGoal: Goal = goalMap.get(goalName)!!
            modGoal.entries.add(newEntry)
            return true
        }
        else {
            return false
        }
    }

    fun modifyGoalNewName(goalName : String, newName : String): Boolean {
        if(goalMap.contains(goalName)) {
            var modGoal: Goal = goalMap.get(goalName)!!
            var savedAmount : Int = modGoal.goalAmount
            var savedDate : Date = modGoal.goalDate
            var savedEntries : MutableList<Pair<Date, Int>> = modGoal.entries

            goalMap.remove(goalName)
            goalMap.put(newName, Goal(
                goalNameInit = newName,
                goalAmountInit = savedAmount,
                goalDateInit = savedDate,
                entriesInit = savedEntries
            ))
            return true
        }
        else {return false}
    }

    override fun updateJson(): Boolean {
        specRoot.asJsonArray.apply {
            for(index in 0..(this.size() - 1))
                this.remove(0)
        }
        var valueMap : List<Goal> = goalMap.values.toList()
        for(element in valueMap) {
            var inputObject = JsonObject()
            inputObject.addProperty("name", element.goalName)
            inputObject.addProperty("amount", element.goalAmount)
            inputObject.addProperty("date", element.goalDate.toString())
            inputObject.add("entries", JsonArray().apply {
                for(entry in element.entries) {
                    var entryObject : JsonObject = JsonObject()
                    entryObject.addProperty("date", entry.first.toString())
                    entryObject.addProperty("amount", entry.second)
                    this.add(entryObject)
                }
            })
            specRoot.asJsonArray.add(inputObject)
        }
        return true
    }

    init {
        goalMap = initGoalMap
    }
}