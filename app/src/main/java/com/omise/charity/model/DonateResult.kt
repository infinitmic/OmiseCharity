package com.omise.charity.model

interface DonateResult

data class DonateForm(val name: String, val token: String, val amount: Int)

data class Donate(
    val success: Boolean?,
    val errorCode: String?,
    val errorMessage: String?
) : DonateResult
