package Debt

import java.util.*

class Debt(
    debtNameInit : String = "Unnamed Debt",
    debtAmountInit : Int = 0,
    debtDateInit : Date = Date(),
    debtOwnerInit : DebtOwner = DebtOwner("Unnamed Lender", ""),
    entriesInit : MutableList<Pair<Date, Int>> = mutableListOf()
) {
    var debtName : String
    var debtAmount : Int
    var debtDate : Date
    var owedTo : DebtOwner
    var entries : MutableList<Pair<Date, Int>>

    init {
        debtName = debtNameInit
        debtAmount = debtAmountInit
        debtDate = debtDateInit
        owedTo = debtOwnerInit
        entries = entriesInit
    }
}