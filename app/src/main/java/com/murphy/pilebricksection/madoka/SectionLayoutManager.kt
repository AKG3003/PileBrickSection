package com.murphy.pilebricksection.madoka

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import java.lang.Exception
import kotlin.math.abs

class SectionLayoutManager private constructor(
    context: Context,
    count: Int,
    adapter: SectionAdapter,
) :
    GridLayoutManager(context, count) {

    init {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val section = adapter.getSection(position) ?: return 0
                val spanSize = abs(section.getSectionSpanSize())
                val itemSize = abs(section.getItemSpanSize(position))
                if (spanSize == 0) return 0
                return itemSize * count / spanSize
            }
        }
    }

    class Builder {
        private var mAdapter: SectionAdapter? = null

        fun setSectionAdapter(adapter: SectionAdapter): Builder {
            mAdapter = adapter
            return this
        }

        fun build(context: Context): SectionLayoutManager {
            if (mAdapter == null) {
                throw Exception("adapter can't be null!")
            }
            return SectionLayoutManager(context, getLCM(), mAdapter!!)
        }

        private fun getLCM(): Int {
            var lcm = 1
            val adapter = mAdapter!!
            val size = adapter.getSectionSize()
            for (i in 0..size) {
                val section = adapter.getSectionInternal(i) ?: continue
                lcm = SectionUtils.LCM(lcm, section.getSectionSpanSize())
            }
            return lcm
        }
    }
}