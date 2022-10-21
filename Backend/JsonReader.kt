import Budgeting.BudgetEntry
import Budgeting.BudgetParent
import com.google.gson.*
import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

class JsonReader(inputString : String) {
    var gson : JsonParser
    var file : File
    var root : JsonObject

    var income : BudgetParent
    var expenses : BudgetParent
    var tags : MutableList<String>

    init {
        gson = JsonParser()
        file = File(inputString)
        root = gson.parse(FileReader(file)).asJsonObject

        var incomeRoot : JsonObject = root.get("income").asJsonObject
        var incomeEntries : JsonArray = incomeRoot.get("entries").asJsonArray
        var incomeEntryQueue : PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>(Comparator<BudgetEntry> { a, b ->
            a.date.compareTo(b.date)
        })

        for (element in incomeEntries) {
            var dateVal = element.asJsonObject.get("date").asString
            var tags = element.asJsonObject.get("tags").asJsonArray
            var tagList : MutableList<String> = mutableListOf()
            for (tagEl in tags) { tagList.add(tagEl.asString) }

            incomeEntryQueue.add(BudgetEntry(
                name = element.asJsonObject.get("name").asString,
                date = Date(
                    dateVal.substring(0..3).toInt(),
                    dateVal.substring(5, 6).toInt(),
                    dateVal.substring(8, 9).toInt()
                ),
                value = element.asJsonObject.get("value").asInt,
                tags = tagList
            ))
        }
        income = BudgetParent(incomeRoot.get("total").asInt, incomeEntryQueue)

        var expenseRoot : JsonObject = root.get("expenses").asJsonObject
        var expenseEntries : JsonArray = expenseRoot.get("entries").asJsonArray
        var expenseEntryQueue : PriorityQueue<BudgetEntry> = PriorityQueue<BudgetEntry>(Comparator<BudgetEntry> { a, b ->
            a.date.compareTo(b.date)
        })

        for (element in expenseEntries) {
            var dateVal = element.asJsonObject.get("date").asString
            var tags = element.asJsonObject.get("tags").asJsonArray
            var tagList : MutableList<String> = mutableListOf()
            for (tagEl in tags) { tagList.add(tagEl.asString) }

            expenseEntryQueue.add(BudgetEntry(
                name = element.asJsonObject.get("name").asString,
                date = Date(
                    dateVal.substring(0..3).toInt(),
                    dateVal.substring(5, 6).toInt(),
                    dateVal.substring(8, 9).toInt()
                ),
                value = element.asJsonObject.get("value").asInt,
                tags = tagList
            ))
        }
        expenses = BudgetParent(expenseRoot.get("total").asInt, expenseEntryQueue)

        println(GsonBuilder().setPrettyPrinting().create().toJson(root))
        var tagArray : JsonArray = root.get("all_tags").asJsonArray
        var tagList : MutableList<String> = mutableListOf()
        for (tag in tagArray)
            tagList.add(tag.asString)
        tags = tagList
    }

}