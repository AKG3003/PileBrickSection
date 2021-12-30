package com.murphy.pilebricksection.madoka

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SectionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: Any?)

    abstract fun onAttachToViewTree()

    abstract fun onDetachFromViewTree()

//    abstract fun onFailedToRecycleView(result: Boolean)
//
//    abstract fun onViewRecycled()
}