package com.omise.charity.view.donate.screen

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import co.omise.android.models.Token
import com.omise.charity.R
import com.omise.charity.view.common.SingleFragmentActivity
import com.omise.charity.view.common.extra
import com.omise.charity.view.common.getIntent
import com.omise.charity.view.donate.fragments.DonateFragment


class DonateActivity : SingleFragmentActivity(),
    DonateFragment.OnDonate {

    private val token: Token by extra(PAYMENT_TAG)

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

    override fun onDonateSuccess() = showDialog("Your transaction is successful!")
    override fun onDonateFailure(errorMsg: String?) = showDialog("Donation Failed! $errorMsg")
    override fun onDonateError(error: Throwable) = displayMessage(error.localizedMessage)
    override fun displayNoNetworkError() = displayMessage("No Network!")
    override fun displayServerUnreachableError() = displayMessage("Server Unreachable!")
    override fun displayCallFailedError() = displayMessage("Operation Failed!")
    override fun displayGenericErrorMessage(errorMsg: String?) =
        displayMessage("Error Occurred! $errorMsg")

    override fun getPaymentToken(): Token {
        return token
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

    private fun showDialog(msg: String) {

        val builder =
            AlertDialog.Builder(ContextThemeWrapper(this, android.R.style.ThemeOverlay_Material_Dialog))

        with(builder)
        {
            setTitle("Donate")
            setMessage(msg)
            setPositiveButton("OK") { _: DialogInterface, _: Int ->
                finish()
            }
            show()
        }
    }
}