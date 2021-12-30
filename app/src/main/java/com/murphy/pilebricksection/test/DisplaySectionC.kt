package com.murphy.pilebricksection.test

import android.view.ViewGroup
import com.murphy.pilebricksection.entity.SectionBean
import com.murphy.pilebricksection.madoka.Section
import com.murphy.pilebricksection.madoka.SectionHolder

class DisplaySectionC : Section() {
    private var mDataAList = ArrayList<SectionBean.SectionCA>()
    private var mDataBList = ArrayList<SectionBean.SectionCB>()

    override fun bindData(data: Any) {
        mDataAList.clear()
        mDataBList.clear()
        (data as? List<SectionBean.SectionC>)?.forEach { sc ->
            sc.sCAs?.let {
                mDataAList.addAll(it)
            }
            sc.sCBs?.let {
                mDataBList.addAll(it)
            }
        }
        (data as? SectionBean.SectionC)?.let { sc ->
            sc.sCAs?.let {
                mDataAList.addAll(it)
            }
            sc.sCBs?.let {
                mDataBList.addAll(it)
            }
        }
    }

    override fun data(outerPosition: Int): Any? {
        val pos = convertToInnerPosition(outerPosition)
        val sizeA = mDataAList.size
        val sizeB = mDataBList.size
        val size = sizeA + sizeB
        if (pos < 0 || pos >= size) {
            return null
        }
        val yun = pos % 2
        val num = pos / 2
        return if (yun == 0) {
            mDataAList[num]
        } else {
            mDataBList[num]
        }
    }

    override fun size(): Int {
        val sizeA = mDataAList.size
        val sizeB = mDataBList.size
        return sizeA + sizeB
    }

    override fun getViewTypeArray(): IntArray {
        return intArrayOf(2, 3)
    }

    override fun getItemViewType(outerPosition: Int): Int {
        val pos = convertToInnerPosition(outerPosition)
        val sizeA = mDataAList.size
        val sizeB = mDataBList.size
        return if (pos % 2 == 0) {
            2
        } else {
            3
        }
    }

    override fun getSectionSpanSize(): Int {
        return 3
    }

    override fun getItemSpanSize(outerPosition: Int): Int {
        val pos = convertToInnerPosition(outerPosition)
        val sizeA = mDataAList.size
        val sizeB = mDataBList.size
        if (pos % 2 == 0) {
            return 2
        } else {
            return 1
        }
    }

    override fun getItemId(outerPosition: Int): Long {
        return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SectionHolder {
        return if (viewType == 2) {
            SectionCAHolder.create(parent.context, parent)
        } else {
            SectionCBHolder.create(parent.context, parent)
        }
    }
}