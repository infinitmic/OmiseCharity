package com.omise.charity.view.donate.fragments.helper

interface DonateUIValidator {
    fun isNameValid(name: String): Boolean
    fun isAmountValid(amount: Int) : Boolean
}