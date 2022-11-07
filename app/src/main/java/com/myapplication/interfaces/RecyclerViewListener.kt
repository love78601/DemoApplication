package com.myapplication.interfaces

import androidx.recyclerview.widget.RecyclerView

interface RecyclerViewListener {

    fun onViewAttached(holder: RecyclerView.ViewHolder?)

    fun onViewRecycled(holder: RecyclerView.ViewHolder)
}