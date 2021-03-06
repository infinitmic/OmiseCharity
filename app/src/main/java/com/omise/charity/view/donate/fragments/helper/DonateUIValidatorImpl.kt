package com.omise.charity.view.donate.fragments.helper


class DonateUIValidatorImpl : DonateUIValidator {
    override fun isNameValid(name: String): Boolean {
        return !name.isBlank()
    }

    override fun isAmountValid(amount: Int): Boolean {
        return amount > 0
    }
}