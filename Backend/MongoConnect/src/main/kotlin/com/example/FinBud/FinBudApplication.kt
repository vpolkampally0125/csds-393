package com.example.FinBud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = arrayOf("com.example.FinBud"))
@SpringBootApplication(exclude = arrayOf(DataSourceAutoConfiguration::class))
class FinBudApplication {}

fun main(args: Array<String>) {
	runApplication<FinBudApplication>(*args)
}