package com.omise.charity.view.donate.fragments.helper

interface DonateErrorMessage {
    fun invalidName(): String
    fun invalidToken(): String
    fun invalidAmount(): String
}