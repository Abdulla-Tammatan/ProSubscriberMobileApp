package com.example.prosubscriberapp.routes

import com.example.prosubscriberapp.service.TransactionService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.transactionRoutes(transactionService: TransactionService) {
    route("/transactions") {

        // View transaction history
        get("/") {
            val userId = call.parameters["userId"]?.toIntOrNull()

            if (userId != null && userId > 0) {
                val transactionHistory = transactionService.getTransactionHistory(userId)
                if (transactionHistory.isNotEmpty()) {
                    call.respond(transactionHistory)
                } else {
                    call.respond(
                        HttpStatusCode.OK,
                        mapOf("message" to "No transaction history found for the user")
                    )
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid or missing user ID")
            }
        }

    }
}
