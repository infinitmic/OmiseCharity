package com.omise.charity.view.donate.screen

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.omise.charity.R
import com.omise.charity.view.common.SingleFragmentActivity
import com.omise.charity.view.common.action
import com.omise.charity.view.common.snack
import com.omise.charity.view.donate.fragments.DonateFragment


class DonateActivity : SingleFragmentActivity(),
    DonateFragment.OnDonate {
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

    override fun onDonateSuccess() {
        displayMessage("Successfully Donated!")
    }

    override fun onDonateFailure(errorMsg: String?) = displayMessage("Donation Failed! $errorMsg")
    override fun onDonateError(error: Throwable) = displayMessage(error.localizedMessage)
    override fun displayNoNetworkError() = displayMessage("No Network!")
    override fun displayServerUnreachableError() = displayMessage("Server Unreachable!")
    override fun displayCallFailedError() = displayMessage("Operation Failed!")
    override fun displayGenericErrorMessage(errorMsg: String?) =
        displayMessage("Error Occurred! $errorMsg")

    private fun displayMessage(msg: String) {
        window.decorView.rootView
            .snack(msg) {
                action("OK") { }
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}