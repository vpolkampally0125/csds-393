package Debt

class DebtOwner(
    initName : String = "Unnamed Lender",
    initContactInfo : String = "No Contact Info Provided"
) {
    var name : String
    var contactInfo : String

    init {
        name = initName
        contactInfo = initContactInfo
    }
}