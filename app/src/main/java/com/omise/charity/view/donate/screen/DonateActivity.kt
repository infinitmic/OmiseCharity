package com.omise.charity.view.donate.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.omise.android.models.Token
import com.omise.charity.R
import com.omise.charity.view.common.SingleFragmentActivity
import com.omise.charity.view.common.extra
import com.omise.charity.view.common.getIntent
import com.omise.charity.view.donate.fragments.DonateFragment


class DonateActivity : SingleFragmentActivity(),
    DonateFragment.OnDonate {

    val token: Token by extra(PAYMENT_TAG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.label_donate)
    }

    override fun createFragment(): Fragment {
        val fragment: DonateFragment = DonateFragment.newInstance()
        fragment.setParentCallback(this)
        return fragment
    }


    override fun onDonateFailure(errorMsg: String?) = displayMessage("Donation Failed! $errorMsg")
    override fun onDonateError(error: Throwable) = displayMessage(error.localizedMessage)
    override fun displayNoNetworkError() = displayMessage("No Network!")
    override fun displayServerUnreachableError() = displayMessage("Server Unreachable!")
    override fun displayCallFailedError() = displayMessage("Operation Failed!")
    override fun displayGenericErrorMessage(errorMsg: String?) =
        displayMessage("Error Occurred! $errorMsg")

    override fun getPaymentToken(): Token {
        return token
    }

    override fun onDonateSuccess() {
        displayMessage("Successfully Donated!")
    }

    companion object {
        private const val PAYMENT_TAG = "com.omise.charity.view.donate.screen.DonateActivity.Token"

        private fun getDonateIntent(context: Context, token: Token?) = context
            .getIntent<DonateActivity>()
            .apply { putExtra(PAYMENT_TAG, token) }

        fun start(context: Context, token: Token?) {
            val intent = getDonateIntent(context, token)
            context.startActivity(intent)
        }
    }
}