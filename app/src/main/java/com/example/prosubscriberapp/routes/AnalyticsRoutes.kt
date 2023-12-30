package com.example.prosubscriberapp.routes

import com.example.prosubscriberapp.service.AnalyticalService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.analyticsRoutes() {
    route("/api/analytics") {
        get {
            call.respondText("Welcome to Analytics!")
        }

        get("/usage") {
            val userIdParam = call.parameters["userId"]?.toIntOrNull()
            val timeframeParam = call.parameters["timeframe"] ?: "day"

            if (userIdParam != null) {
                val analyticsData = AnalyticalService.getSubscriptionAnalytics(userIdParam, timeframeParam)
                call.respond(HttpStatusCode.OK, analyticsData)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid userId parameter")
            }
        }
    }
}
