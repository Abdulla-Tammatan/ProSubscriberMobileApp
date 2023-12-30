package com.example.prosubscriberapp.service

import com.example.prosubscriberapp.config.AppUsage
import com.example.prosubscriberapp.config.Subscriptions
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.time.LocalDateTime

object AnalyticalService {
    fun getSubscriptionAnalytics(userId: Int, timeframe: String): List<SubscriptionAnalytics> {
        return transaction {
            val subIds = Subscriptions.select { Subscriptions.userId eq userId }.map { it[Subscriptions.subId] }

            val startDate = when (timeframe) {
                "day" -> LocalDateTime.now().minusDays(1)
                "week" -> LocalDateTime.now().minusWeeks(1)
                "month" -> LocalDateTime.now().minusMonths(1)
                else -> LocalDateTime.now().minusDays(1)
            }

            AppUsage
                .join(Subscriptions, JoinType.INNER, additionalConstraint = { Subscriptions.subId inList subIds })
                .slice(AppUsage.dateMeasured, AppUsage.numHours)
                .select { (AppUsage.dateMeasured greaterEq startDate) }
                .groupBy(AppUsage.dateMeasured)
                .map { row ->
                    SubscriptionAnalytics(
                        dateMeasured = row[AppUsage.dateMeasured],
                        totalUsageHours = row[AppUsage.numHours] ?: 0
                    )
                }
        }
    }
}

data class SubscriptionAnalytics(val dateMeasured: Instant, val totalUsageHours: Int)
