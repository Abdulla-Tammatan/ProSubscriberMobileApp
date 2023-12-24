package com.example.prosubscriberapp.config

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

//Exposed Table
object Users : IntIdTable() {
    val userId = integer("userId").autoIncrement().primaryKey()
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 255)
    val age = integer("age")
    val name = varchar("name", 255)
    val location = varchar("location", 255)
    val gender = varchar("gender", 255)
    val deviceType = varchar("deviceType", 255)
    val gaid = varchar("gaid", 255)
}

class DatabaseConfig {
    init {
        Database.connect(
            url = "jdbc:mysql://<YOUR_MYSQL_HOST>:<YOUR_MYSQL_PORT>/practice",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "<YOUR_MYSQL_USERNAME>",
            password = "<YOUR_MYSQL_PASSWORD>"
        )

        transaction {
            // Auto-generate missing tables based on Exposed entities
            SchemaUtils.create(Users)
        }
    }
}