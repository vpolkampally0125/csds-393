package com.example.FinBud.service

import com.example.FinBud.model.DbSeq
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.where
import org.springframework.stereotype.Service
import java.util.*


@Service
class SequenceGeneratorService @Autowired constructor(private val mongoOperations: MongoOperations) {
    fun generateSequence(seqName: String?): Long {
        val counter: DbSeq = mongoOperations.findAndModify(
            query(where(DbSeq::id).`is`(seqName)),
            Update().inc("seq", 1), options().returnNew(true).upsert(true),
            DbSeq::class.java
        )!!
        return if (!Objects.isNull(counter)) counter.getSeq() else 1
    }
}