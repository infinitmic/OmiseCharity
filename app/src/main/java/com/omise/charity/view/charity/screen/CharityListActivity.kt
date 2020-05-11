package com.omise.charity.view.charity.screen

import androidx.fragment.app.Fragment
import com.omise.charity.network.dto.Charity
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

    override fun loadDonations(charity: Charity) {
        window.decorView.rootView
            .snack("Feature Not Implemented !!") {
                action("OK") { }
            }
    }

    override fun showError(error: Throwable) =
        displayMessage("Loading Failed: ${error.localizedMessage}")

    override fun showMessage(data: String) = displayMessage(data)

    private fun displayMessage(data: String) {
        window.decorView.rootView
            .snack(data) {
                action("OK") { }
            }
    }
}