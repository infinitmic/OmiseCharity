package com.omise.charity.view.common

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.omise.charity.R

abstract class SingleFragmentActivity : BaseActivity() {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_single_fragment

    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        addOnlyOnce(createFragment(), R.id.fragment_container)
    }

    protected fun displayMessage(data: String) {
        window.decorView.rootView
            .snack(data) {
                action("OK") { }
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}