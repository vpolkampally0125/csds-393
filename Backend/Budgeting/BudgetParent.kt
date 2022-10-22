package Budgeting

import java.util.*

class BudgetParent(
    var totalValue : Int,
    var entries : PriorityQueue<BudgetEntry>
    ) {
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

    fun addValue(entry : BudgetEntry){
        entries.add(entry);
        totalValue += entry.value
    }

    fun removeValue(entry : BudgetEntry) {
        entries.remove(entry)
        totalValue -= entry.value
    }

    fun tagEntry(entry : BudgetEntry, tag : String): MutableList<String> {
        entry.tags.add(tag)
        return entry.tags
    }

    fun removeTagFromEntry(entry : BudgetEntry, tag : String) { entry.tags.remove(tag) }

    fun updateTotalVal() {
        var totalVal = 0

        for (value in entries) {
            totalVal += value.value
        }

        totalValue = totalVal
    }

}

data class BudgetEntry(
    var name : String,
    var date : Date,
    var value : Int,
    var tags : MutableList<String>
)