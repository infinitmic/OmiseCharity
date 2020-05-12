package com.omise.charity.view.donate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.omise.android.models.Token
import com.omise.charity.R
import com.omise.charity.model.DonateForm
import com.omise.charity.view.common.BaseFragmentWithPresenter
import kotlinx.android.synthetic.main.fragment_donate.*
import javax.inject.Inject

class DonateFragment : BaseFragmentWithPresenter(), DonateFragmentView {

    @Inject
    override lateinit var presenter: DonateFragmentPresenter

    lateinit var callback: OnDonate

    lateinit var token: Token


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_donate, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        token = callback.getPaymentToken()
        btnSubmit.setOnClickListener { presenter.donate(getDonateForm()) }
        rlRootDonate.setOnClickListener { clearFocus() }
        etvName.setText(token.card?.name)
    }

    companion object {
        fun newInstance() = DonateFragment()
    }

    override fun showError(error: Throwable) {
        callback.onDonateError(error)
    }

    override fun clearErrors() {
        clearAmountError()
    }


    override fun clearAmountError() {
        etvAmount.error = null
    }

    override fun invalidAmount(errorMsg: String) {
        etvAmount.error = errorMsg
    }

    override fun onDonateSuccess() {
        callback.onDonateSuccess()
    }

    override fun onDonateFailure(errorMsg: String?) {
        callback.onDonateFailure(errorMsg)
    }

    override fun showProgress() {
        btnSubmit.text = getString(R.string.label_common_processing_request)
    }

    override fun hideProgress() {
        btnSubmit.text = getString(R.string.label_donate_submit)
    }

    override fun enableAllInput() {
        etvAmount.isEnabled = true

        btnSubmit.isEnabled = true
    }

    override fun disableAllInput() {

        etvAmount.clearFocus()
        etvAmount.isEnabled = false

        btnSubmit.isEnabled = false
    }

    override fun removeCallbacks() {
        btnSubmit.setOnClickListener(null)
        rlRootDonate.setOnClickListener(null)
    }

    override fun displayNoNetworkError() {
        callback.displayNoNetworkError()
    }

    override fun displayServerUnreachableError() {
        callback.displayServerUnreachableError()
    }

    override fun displayCallFailedError() {
        callback.displayCallFailedError()
    }

    override fun displayGenericErrorMessage(errorMsg: String?) {
        callback.displayGenericErrorMessage(errorMsg)
    }

    private fun getDonateForm(): DonateForm = DonateForm(
        token.card?.name,
        token.id,
        if (etvAmount.text.toString().isBlank()) 0 else etvAmount.text.toString().toInt()
    )

    fun setParentCallback(callback: OnDonate) {
        this.callback = callback
    }

    private fun clearFocus() {
        etvAmount.clearFocus()
    }

    // This interface can be implemented by the Activity, parent Fragment,
    interface OnDonate {
        fun onDonateSuccess()
        fun onDonateFailure(errorMsg: String?)
        fun onDonateError(error: Throwable)
        fun displayNoNetworkError()
        fun displayServerUnreachableError()
        fun displayCallFailedError()
        fun displayGenericErrorMessage(errorMsg: String?)
        fun getPaymentToken(): Token
    }
}