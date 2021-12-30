package com.murphy.pilebricksection.test

import android.view.ViewGroup
import com.murphy.pilebricksection.entity.SectionBean
import com.murphy.pilebricksection.madoka.Section
import com.murphy.pilebricksection.madoka.SectionHolder

class DisplaySectionA : Section() {
    private var mDataList = ArrayList<SectionBean.SectionA>()

    override fun bindData(data: Any) {
        mDataList.clear()
        (data as? List<SectionBean.SectionA>)?.let {
            mDataList.addAll(it)
            return
        }
        (data as? SectionBean.SectionA)?.let {
            mDataList.add(it)
        }
    }

    override fun data(outerPosition: Int): Any? {
        val pos = convertToInnerPosition(outerPosition)
        if (pos < 0 || pos >= mDataList.size) {
            return null
        }
        return mDataList[pos]
    }

    override fun size(): Int {
        return mDataList.size
    }

    override fun getViewTypeArray(): IntArray {
        return intArrayOf(1)
    }

    override fun getItemViewType(outerPosition: Int): Int {
        return 1
    }

    override fun getSectionSpanSize(): Int {
        return 1
    }

    override fun getItemSpanSize(outerPosition: Int): Int {
        return 1
    }

    override fun getItemId(outerPosition: Int): Long {
        return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SectionHolder {
        return SectionAHolder.create(parent.context, parent)
    }
}