package com.example.FinBud.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Document(collection = "Transaction")
class Transaction() {
    constructor(iName : String, iType : String, iAmount : Float, iDate : String) : this() {
        name = iName
        type = iType
        amount = iAmount
        date = iDate
    }

    @Transient
    val SEQUENCE_NAME : String = "users_sequence"

    @Id
    private var id : Long = 0

    @Size(max = 100)
    @Indexed(unique = true)
    private var name : @NotBlank String? = null
    private var type : String? = null
    private var amount : Float? = null
    private var date : String? = null

    fun getName() : String? { return name }
    fun setName(newName : String) { name = newName }

    fun getType() : String? {return type}
    fun setType(newType : String) { type = newType }

    fun getAmount() : Float? { return amount }
    fun setAmount(newAmount : Float) { amount = newAmount }

    fun getDate() : String? {return date}
    fun setDate(newDate : String) { date = newDate }

    fun getId(): Long { return id }

    fun setId(newId: Long) { id = newId }

    override fun toString() : String { return "Transaction [id=$id, name=$name, type=$type, amount=$amount, date=$date]" }
}