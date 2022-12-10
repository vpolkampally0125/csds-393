package com.example.FinBud.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "database_sequences")
class DbSeq() {

    @Id
    var id : String? = null

    private var seq : Long? = null

    public fun getID() : String {return id!!}

    public fun setID(newID : String) {id = newID}

    public fun getSeq() : Long {return seq!!}

    public fun setID(newSeq : Long) {seq = newSeq}
}