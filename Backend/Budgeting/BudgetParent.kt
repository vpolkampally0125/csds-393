package Budgeting

import JsonUpdateable
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.util.*

class BudgetParent(
    var totalValue : Int,
    var entries : PriorityQueue<BudgetEntry>,
    filePath : String,
    jsonHeading : String,
    ) : JsonUpdateable(filePath, jsonHeading) {
    var currentDate : Date = Date()

    fun getTot() : Int { return totalValue; }

    fun removePastNodes() {
        var reverseQueue : PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>(Comparator<BudgetEntry> { a, b ->
            b.date.compareTo(a.date)
        })
        reverseQueue.addAll(entries);

        var currentElement = reverseQueue.poll()
        while (currentElement.date.compareTo(currentDate) == -1)
            entries.remove(currentElement)
    }

    fun addValue(entry : BudgetEntry) {
        entries.add(entry);
        totalValue += entry.value
    }

    fun removeValue(entry : BudgetEntry): Boolean {
        entries.remove(entry)
        totalValue -= entry.value
        return true
    }

    fun tagEntry(entry : BudgetEntry, tag : String): String {
        entry.tags.add(tag)
        return tag
    }

    fun removeTagFromEntry(entry : BudgetEntry, tag : String): Boolean {
        entry.tags.remove(tag)
        return true
    }

    fun updateTotalVal(): Boolean {
        var totalVal = 0

        for (value in entries) {
            totalVal += value.value
        }

        totalValue = totalVal

        return true
    }

    override fun updateJson() : Boolean {
        specRoot.remove("total")
        specRoot.remove("entries")

        var updatedEntries : JsonArray = JsonArray()
        for(element in entries) {
            var inputObject = JsonObject()
            inputObject.addProperty("name", element.name)
            inputObject.addProperty("date", element.date.toString())
            inputObject.addProperty("value", element.value.toString())
            inputObject.add("tags", JsonArray().apply {
                for (tag in element.tags) {
                    this.add(tag)
                }
            })
            updatedEntries.add(inputObject)

        }
        specRoot.addProperty("total", getTot())
        specRoot.add("entries", updatedEntries)

        return true
    }

}

data class BudgetEntry(
    var name : String,
    var date : Date,
    var value : Int,
    var tags : MutableList<String>
)