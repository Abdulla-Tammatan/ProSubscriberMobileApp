package com.example.prosubscriberapp.model

import java.sql.Timestamp

data class Subscription(
    private val subID: Long,
    private val type: String?,
    private val startDate: Timestamp?,
    private val endDate: Timestamp?,
    private val paymentFrequency: Int?,
    private val userID: Int
    )

{
    fun getSubID(): Long = subID
    fun getType(): String? = type
    fun getStartDate(): Timestamp? = startDate
    fun getEndDate(): Timestamp? = endDate
    fun getPaymentFrequency(): Int? = paymentFrequency
    fun getUserID(): Int = userID
}