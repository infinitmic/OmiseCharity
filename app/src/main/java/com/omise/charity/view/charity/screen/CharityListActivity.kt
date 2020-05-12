package com.omise.charity.view.charity.screen

import android.content.Intent
import androidx.fragment.app.Fragment
import co.omise.android.models.Token
import co.omise.android.ui.CreditCardActivity
import co.omise.android.ui.OmiseActivity
import co.omise.android.ui.OmiseActivity.Companion.EXTRA_TOKEN_OBJECT
import com.omise.charity.BuildConfig
import com.omise.charity.R
import com.omise.charity.model.Charity
import com.omise.charity.view.charity.fragments.CharityListFragment
import com.omise.charity.view.common.SingleFragmentActivity
import com.omise.charity.view.donate.screen.DonateActivity

class CharityListActivity : SingleFragmentActivity(), CharityListFragment.OnCharity {

    private val REQUEST_CC: Int = 100

    override fun createFragment(): Fragment {
        val fragment: CharityListFragment = CharityListFragment.newInstance()
        fragment.setParentCallback(this)
        return fragment
    }

    override fun showError(error: Throwable) =
        displayMessage("Loading Failed!: ${error.localizedMessage}")

    override fun loadDonations(charity: Charity) = onDonateClick()
    override fun showMessage(data: String) = displayMessage(data)
    override fun displayNoNetworkError() = displayMessage("No Network!")
    override fun displayServerUnreachableError() = displayMessage("Server Unreachable!")
    override fun displayCallFailedError() = displayMessage("Operation Failed!")
    override fun displayGenericErrorMessage(errorMsg: String?) =
        displayMessage("Unknown Error Occurred!")

    override fun onDonateClick() {
        showCreditCardForm()
    }

    private fun showCreditCardForm() {
        val intent = Intent(this, CreditCardActivity::class.java)
        intent.putExtra(OmiseActivity.EXTRA_PKEY, BuildConfig.OMISE_PUBLIC_API_KEY)
        startActivityForResult(intent, REQUEST_CC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            displayMessage(getString(R.string.label_request_cancelled))
            return
        }

        if (requestCode == REQUEST_CC) {
            val token = data?.getParcelableExtra<Token>(EXTRA_TOKEN_OBJECT)
            DonateActivity.start(this, token)
        }
    }
}