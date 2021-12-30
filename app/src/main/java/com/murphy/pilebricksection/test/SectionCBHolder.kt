package com.murphy.pilebricksection.test

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.murphy.pilebricksection.R
import com.murphy.pilebricksection.entity.SectionBean
import com.murphy.pilebricksection.madoka.SectionHolder

class SectionCBHolder(itemView: View) : SectionHolder(itemView) {
    override fun bind(data: Any?) {
        (data as? SectionBean.SectionCB)?.let {
            val view = itemView.findViewById<TextView>(R.id.tv_look_num)
            view?.text = data.scbText
            view?.setBackgroundColor(Color.RED)
        }
    }

    override fun onAttachToViewTree() {
    }

    override fun onDetachFromViewTree() {
    }

    companion object {
        fun create(context: Context, parent: ViewGroup): SectionCBHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_demo_layout, parent, false)
            return SectionCBHolder(view)
        }
    }
}