package com.omise.charity.view.charity.screen

import androidx.fragment.app.Fragment
import com.omise.charity.model.Charity
import com.omise.charity.view.charity.fragments.CharityListFragment
import com.omise.charity.view.common.SingleFragmentActivity
import com.omise.charity.view.common.action
import com.omise.charity.view.common.snack

class CharityListActivity : SingleFragmentActivity(), CharityListFragment.OnCharity {

    override fun createFragment(): Fragment {
        val fragment: CharityListFragment = CharityListFragment.newInstance()
        fragment.setParentCallback(this)
        return fragment
    }

    override fun showError(error: Throwable) =
        displayMessage("Loading Failed: ${error.localizedMessage}")

    override fun loadDonations(charity: Charity) = displayMessage("Feature Not Implemented!")
    override fun showMessage(data: String) = displayMessage(data)
    override fun displayNoNetworkError() = displayMessage("No Network!")
    override fun displayServerUnreachableError() = displayMessage("Server Unreachable!")
    override fun displayCallFailedError() = displayMessage("Operation Failed!")
    override fun displayGenericErrorMessage(errorMsg: String?) =
        displayMessage("Unknown Error Occurred!")

    private fun displayMessage(data: String) {
        window.decorView.rootView
            .snack(data) {
                action("OK") { }
            }
    }
}