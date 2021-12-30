package com.murphy.pilebricksection.madoka

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Section
 * innerPosition:内部自身数据索引
 * outerPosition:外部所有Section的序列
 */
abstract class Section {
    private var startPosition = RecyclerView.NO_POSITION

    fun setStart(outerPosition: Int) {
        startPosition = outerPosition
    }

    fun getStart(): Int {
        return startPosition
    }

    abstract fun bindData(data: Any)

    abstract fun data(outerPosition: Int): Any?

    abstract fun size(): Int

    abstract fun getViewTypeArray(): IntArray

    /**
     * 同一个Section里面允许有多个不同的holder类型，定义ViewType
     */
    abstract fun getItemViewType(outerPosition: Int): Int

    /**
     * 设定此Section占位数目
     */
    abstract fun getSectionSpanSize(): Int

    /**
     * 不同的holder可以定义不同的占位数目
     */
    abstract fun getItemSpanSize(outerPosition: Int): Int

    /**
     * 这里的id强烈不推荐直接使用position，尽量采取换算的方式保证唯一性
     */
    abstract fun getItemId(outerPosition: Int): Long

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder?

    fun convertToInnerPosition(outerPosition: Int): Int {
        return if (startPosition == RecyclerView.NO_POSITION) {
            RecyclerView.NO_POSITION
        } else {
            outerPosition - startPosition
        }
    }
}