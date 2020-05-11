package com.omise.charity.view.charity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.omise.charity.R
import com.omise.charity.model.Charity
import com.omise.charity.model.CharityList
import com.omise.charity.view.common.BaseFragmentWithPresenter
import com.omise.charity.view.common.bindToSwipeRefresh
import kotlinx.android.synthetic.main.fragment_charity_list.*
import javax.inject.Inject

class CharityListFragment : BaseFragmentWithPresenter(), CharityListFragmentView {

    @Inject
    override lateinit var presenter: CharityListFragmentPresenter

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    private lateinit var callback: OnCharity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_charity_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        btnDonations.setOnClickListener { callback.onDonateClick() }
        presenter.onViewCreated()
    }

    companion object {
        fun newInstance() = CharityListFragment()
    }

    override fun show(item: CharityList) {
        val charityItemsAdapter = item.charityList!!.map(this::createCharityItemsAdapter)
        mRecyclerView.adapter = CharityListAdapter(charityItemsAdapter)
    }

    private fun createCharityItemsAdapter(charity: Charity) =
        CharityItemAdapter(charity) { loadCharityDetails(charity) }

    private fun loadCharityDetails(charity: Charity) {
        callback.loadDonations(charity)
    }

    override fun showError(error: Throwable) {
        callback.showError(error)
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

    override fun showMessage(data: String) {
        callback.showMessage(data)
    }

    fun setParentCallback(callback: OnCharity) {
        this.callback = callback
    }

    override fun onDestroyView() {
        super.onDestroyView()
        swipeRefreshView.setOnRefreshListener(null)
        btnDonations.setOnClickListener(null)
    }

    interface OnCharity {
        fun showError(error: Throwable)
        fun loadDonations(charity: Charity)
        fun showMessage(data: String)
        fun displayNoNetworkError()
        fun displayServerUnreachableError()
        fun displayCallFailedError()
        fun displayGenericErrorMessage(errorMsg: String?)
        fun onDonateClick()
    }
}