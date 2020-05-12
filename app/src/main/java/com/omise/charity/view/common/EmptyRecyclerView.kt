package com.omise.charity.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

class EmptyRecyclerView : RecyclerView {
    private var mEmptyView: View? = null
    private var mParentView: View? = null

    constructor(context: Context?) : super(context!!)
    constructor(
        context: Context?,
        @Nullable attrs: AttributeSet?
    ) : super(context!!, attrs)

    constructor(
        context: Context?,
        @Nullable attrs: AttributeSet?,
        defStyle: Int
    ) : super(context!!, attrs, defStyle)

    private fun initEmptyView() {
        if (mEmptyView != null) {
            mEmptyView!!.visibility =
                if (adapter == null || adapter!!.itemCount == 0) View.VISIBLE else View.GONE
            this@EmptyRecyclerView.visibility =
                if (adapter == null || adapter!!.itemCount == 0) View.GONE else View.VISIBLE
            mParentView?.visibility = this@EmptyRecyclerView.visibility
        }
    }

    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            initEmptyView()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            initEmptyView()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            initEmptyView()
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        super.setAdapter(adapter)
        oldAdapter?.unregisterAdapterDataObserver(observer)
        adapter?.registerAdapterDataObserver(observer)
        initEmptyView()
    }

    fun setEmptyView(view: View?) {
        mEmptyView = view
        initEmptyView()
    }

    fun setParentView(view: View?) {
        mParentView = view
    }
}