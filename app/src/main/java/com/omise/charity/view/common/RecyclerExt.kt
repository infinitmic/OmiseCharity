@file:Suppress("UNCHECKED_CAST")

package com.omise.charity.view.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun <T : View> RecyclerView.ViewHolder.bindView(viewId: Int) =
    lazy { itemView.findViewById<T>(viewId) }