import Budgeting.BudgetEntry
import Budgeting.BudgetParent
import Debt.Debt
import Debt.DebtMain
import FinancialGoals.FinancialGoals
import FinancialGoals.Goal
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
    var goals : FinancialGoals
    var debts : DebtMain
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
        income = BudgetParent(incomeRoot.get("total").asInt, incomeEntryQueue, inputString, "income")

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
        expenses = BudgetParent(expenseRoot.get("total").asInt, expenseEntryQueue, inputString, "expenses")


        var goalRoot : JsonArray = root.get("goals").asJsonObject.get("goals").asJsonArray
        var goalMap : MutableMap<String, Goal> = mutableMapOf()

        for(element in goalRoot) {
            var dateVal : String = element.asJsonObject.get("date").asString

            var entriesArray : JsonArray = element.asJsonObject.get("entries").asJsonArray
            var entriesInputList : MutableList<Pair<Date, Int>> = mutableListOf()

            goalMap.put(
                element.asJsonObject.get("name").asString,
                Goal(
                    goalNameInit = element.asJsonObject.get("name").asString,
                    goalAmountInit = element.asJsonObject.get("amount").asInt,
                    goalDateInit = Date(
                        dateVal.substring(0..3).toInt(),
                        dateVal.substring(5, 6).toInt(),
                        dateVal.substring(8, 9).toInt()
                    ),
                    entriesInit = entriesInputList.apply {
                        for (entrEl in entriesArray) {
                            var stringDateVal: String = entrEl.asJsonObject.get("date").asString
                            var inputDateVal: Date = Date(
                                stringDateVal.substring(0..3).toInt(),
                                stringDateVal.substring(5, 6).toInt(),
                                stringDateVal.substring(8, 9).toInt()
                            )

                            this.add(Pair(inputDateVal, entrEl.asJsonObject.get("amount").asInt))
                        }
                    }
                ))
        }

        goals = FinancialGoals(
            initGoalMap = goalMap,
            filePath = inputString,
            jsonHeading = "goals"
        )

        var debtRoot : JsonArray = root.get("debts").asJsonObject.get("debts").asJsonArray
        var debtMap : MutableMap<String, Debt> = mutableMapOf()

        for(element in debtRoot) {
            var dateVal : String = element.asJsonObject.get("date").asString

            var entriesArray : JsonArray = element.asJsonObject.get("entries").asJsonArray
            var entriesInputList : MutableList<Pair<Date, Int>> = mutableListOf()

            debtMap.put(
                element.asJsonObject.get("name").asString,
                Debt(
                    debtNameInit = element.asJsonObject.get("name").asString,
                    debtAmountInit = element.asJsonObject.get("amount").asInt,
                    debtDateInit = Date(
                        dateVal.substring(0..3).toInt(),
                        dateVal.substring(5, 6).toInt(),
                        dateVal.substring(8, 9).toInt()
                    ),
                    entriesInit = entriesInputList.apply {
                        for (entrEl in entriesArray) {
                            var stringDateVal: String = entrEl.asJsonObject.get("date").asString
                            var inputDateVal: Date = Date(
                                stringDateVal.substring(0..3).toInt(),
                                stringDateVal.substring(5, 6).toInt(),
                                stringDateVal.substring(8, 9).toInt()
                            )

                            this.add(Pair(inputDateVal, entrEl.asJsonObject.get("amount").asInt))
                        }
                    }
                ))
        }

        debts = DebtMain(
            initDebtMap = debtMap,
            filePath = inputString,
            jsonHeading = "debts"
        )


        println(GsonBuilder().setPrettyPrinting().create().toJson(root))
        var tagArray : JsonArray = root.get("all_tags").asJsonArray
        var tagList : MutableList<String> = mutableListOf()
        for (tag in tagArray)
            tagList.add(tag.asString)
        tags = tagList
    }

}