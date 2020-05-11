package com.omise.charity.view.common

import android.os.Bundle
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
}