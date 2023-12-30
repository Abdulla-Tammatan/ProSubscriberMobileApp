package com.example.prosubscriberapp.model

import java.time.LocalDateTime

class AppUsage(
    private val dateMeasured: LocalDateTime,
    private val subId: Int,
    private val numHours: Int?
) {
    fun getDateMeasured(): LocalDateTime {
        return dateMeasured
    }

    fun getSubId(): Int {
        return subId
    }

    fun getNumHours(): Int? {
        return numHours
    }
}