package com.murphy.pilebricksection.madoka

import android.annotation.SuppressLint
import android.util.SparseArray
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.recyclerview.widget.RecyclerView

/**
 * Murphy
 * 泛型指代:
 * D -> 数据类型
 * VH -> 使用D数据类型的SectionHolder组件
 * S -> 使用D数据类型的Section组件
 * 目的是为了通过D数据类型绑定SectionHolder和Section的种类，保证兼容。
 * 其中Section本身是对同一块连续显示区域中符合D数据类型构建的SectionHolder的合集。
 * 在同一个Section里面不同的SectionHolder可以通过ViewType进行区分。
 */
class SectionAdapter : RecyclerView.Adapter<SectionHolder>() {
    private val mSections = SparseArray<Section>()
    private val mPosCaches = SparseArray<Section>()
    private val mTypeCaches = SparseArray<Section>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionHolder {
        val section = mTypeCaches[viewType]
        return section.onCreateViewHolder(parent, viewType) as SectionHolder
    }

    override fun onBindViewHolder(holder: SectionHolder, position: Int) {
        holder.bind(getSection(position)?.data(position))
    }

    //:TODO:补充局部刷新的功能
    override fun onBindViewHolder(
        holder: SectionHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onViewAttachedToWindow(holder: SectionHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttachToViewTree()
    }

    override fun onViewDetachedFromWindow(holder: SectionHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetachFromViewTree()
    }

//    override fun onFailedToRecycleView(holder: SH): Boolean {
//        val result = super.onFailedToRecycleView(holder)
//        holder.onFailedToRecycleView(result)
//        return result
//    }
//
//    override fun onViewRecycled(holder: SH) {
//        super.onViewRecycled(holder)
//        holder.onViewRecycled()
//    }

    override fun getItemId(position: Int): Long {
        if (hasStableIds()) {
            return getSection(position)?.getItemId(position) ?: super.getItemId(position)
        }
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return mPosCaches.size()
    }

    override fun getItemViewType(position: Int): Int {
        return getSection(position)?.getItemViewType(position) ?: 0
    }

    fun getSection(position: Int): Section? {
        if (position >= mPosCaches.size() || position < 0) {
            return null
        }
        return mPosCaches[position]
    }

    fun getSectionSize(): Int {
        return mSections.size()
    }

    fun getSectionInternal(sectionPosition: Int): Section? {
        if (sectionPosition >= mSections.size() || sectionPosition < 0) {
            return null
        }
        return mSections[sectionPosition]
    }

    fun getSectionInternalPosition(section: Section?): Int {
        return if (section == null) -1 else mSections.indexOfValue(section)
    }

    fun addSectionInternal(position: Int, section: Section?) {
        mSections.put(position, section)
    }

    fun addSectionInternal(section: Section?) {
        mSections.put(mSections.size(), section)
    }

    fun removeSectionInternal(position: Int) {
        mSections.remove(position)
    }

    fun removeSectionInternal(section: Section?) {
        val pos = mSections.indexOfValue(section)
        if (pos > -1) mSections.remove(pos)
    }

    fun setSections(sections: List<Section>) {
        mSections.clear()
        mPosCaches.clear()
        sections.forEachIndexed { index, section ->
            mSections.put(index, section)
        }
        onSectionsChanged(invalidate = true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSectionsChanged(invalidate: Boolean) {
        mPosCaches.clear()
        var count = 0
        mSections.forEach { _, section ->
            //设置根据位置索引的表
            section.setStart(count)
            val size = section.size()
            for (index in 0..size) {
                mPosCaches.put(count + index, section)
            }
            count += size
            //设置根据类型索引的表
            section.getViewTypeArray().let { array ->
                array.forEach { itemType ->
                    mTypeCaches.put(itemType, section)
                }
            }

        }
        if (invalidate) {
            notifyDataSetChanged()
        }
    }

    fun onDestroy() {
        clear()
    }

    fun clear() {
        mPosCaches.clear()
        mTypeCaches.clear()
        mSections.clear()
    }
}