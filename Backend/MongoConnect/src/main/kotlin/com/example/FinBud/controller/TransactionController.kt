package com.example.FinBud.controller

//import net.guides.springboot.crud.exception.ResourceNotFoundException;
import com.example.FinBud.model.Transaction
import com.example.FinBud.repository.TransactionRepository
import com.example.FinBud.service.SequenceGeneratorService
import com.example.FinBud.RNFException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api")
class TransactionController {
    @Autowired
    private val transactionRepository : TransactionRepository? = null

    @Autowired
    private val sequenceGeneratorService : SequenceGeneratorService? = null

    @GetMapping("/transactions")
    fun getAllEmployees(): kotlin.collections.List<Transaction?>? {
        return transactionRepository!!.findAll()
    }

    @GetMapping("/transactions/{id}")
    @Throws(RNFException::class)
    fun getTransactionID(@PathVariable(value = "id") transactionId : Long) : ResponseEntity<Transaction> {
        var trans : Transaction? = transactionRepository!!.findById(transactionId)
            .orElseThrow { RNFException("A transaction was not found for this ID $transactionId") }
        return ResponseEntity.ok().body(trans)
    }

    @PostMapping("/transactions")
    fun createTransaction(@Valid @RequestBody transaction : Transaction) : Transaction {
        transaction.setId(sequenceGeneratorService!!.generateSequence(transaction.SEQUENCE_NAME));
        return transactionRepository!!.save(transaction);
    }

    @PutMapping("/transactions/{id}")
    @Throws(RNFException::class)
    fun updateTransaction(
        @PathVariable(value = "id") transactionID : Long ,
        @Valid @RequestBody transactionDetails : Transaction ) : ResponseEntity < Transaction > {

        var trans : Transaction? = transactionRepository!!.findById(transactionID)
            .orElseThrow { RNFException("A transaction was not found for this ID $transactionID") }

        trans!!.setName(transactionDetails.getName()!!);
        trans.setType(transactionDetails.getType()!!);
        trans.setAmount(transactionDetails.getAmount()!!);
        trans.setDate(transactionDetails.getDate()!!);
        val upTransaction : Transaction = transactionRepository.save(trans)
        return ResponseEntity.ok(upTransaction);
    }

    @DeleteMapping("/transactions/{id}")
    @Throws(RNFException::class)
    fun deleteEmployee(@PathVariable(value = "id") transactionID : Long) : Map < String, Boolean > {
        var trans : Transaction? = transactionRepository!!.findById(transactionID)
            .orElseThrow { RNFException("A transaction was not found for this ID $transactionID") }

        transactionRepository.delete(trans!!);
        var response : MutableMap < String, Boolean > = HashMap();
        response.put("deleted", true);
        return response;
    }
}