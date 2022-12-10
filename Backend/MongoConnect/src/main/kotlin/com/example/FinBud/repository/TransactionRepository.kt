package com.example.FinBud.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

import com.example.FinBud.model.Transaction

@Repository
interface TransactionRepository : MongoRepository<Transaction?, Long?>