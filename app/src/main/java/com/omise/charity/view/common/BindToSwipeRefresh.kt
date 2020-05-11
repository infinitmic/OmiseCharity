package com.omise.charity.view.common

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Fragment.bindToSwipeRefresh(@IdRes swipeRefreshLayoutId: Int): ReadWriteProperty<Any?, Boolean> =
    SwipeRefreshBinding(lazy { activity!!.findViewById<SwipeRefreshLayout>(swipeRefreshLayoutId) })

private class SwipeRefreshBinding(lazyViewProvider: Lazy<SwipeRefreshLayout>) :
    ReadWriteProperty<Any?, Boolean> {

    val view by lazyViewProvider

    override fun getValue(thisRef: Any?, property: KProperty<*>) = view.isRefreshing

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        view.isRefreshing = value
    }
}