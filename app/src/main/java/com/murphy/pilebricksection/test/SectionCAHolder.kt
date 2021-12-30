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

class SectionCAHolder(itemView: View) : SectionHolder(itemView) {
    override fun bind(data: Any?) {
        (data as? SectionBean.SectionCA)?.let {
            val view = itemView.findViewById<TextView>(R.id.tv_look_num)
            view?.text = data.scaText
            view?.setBackgroundColor(Color.YELLOW)
        }
    }

    override fun onAttachToViewTree() {
    }

    override fun onDetachFromViewTree() {
    }

    companion object {
        fun create(context: Context, parent: ViewGroup): SectionCAHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_demo_layout, parent, false)
            return SectionCAHolder(view)
        }
    }
}