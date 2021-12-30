package com.murphy.pilebricksection.test

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.murphy.pilebricksection.madoka.Section
import com.murphy.pilebricksection.madoka.SectionAdapter
import com.murphy.pilebricksection.madoka.SectionLayoutManager

class SectionGroup {
    private var mAdapter: SectionAdapter? = null
    private var mLayoutManager: SectionLayoutManager? = null

    var mRecyclerView: RecyclerView? = null
    var isAttached: Boolean = false

    /**
     * 传入需要绑定的根节点，会自动创建一个recyclerview并绑定
     */
    fun onAttach(root: ViewGroup) {
        val recycler = RecyclerView(root.context)
        recycler.measure(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        root.addView(recycler)
        mRecyclerView = recycler
        isAttached = true
    }

    /**
     * 自己绑定recyclerview，然后传入这里进行管理
     */
    fun onAttach(recycler: RecyclerView) {
        mRecyclerView = recycler
        isAttached = true
    }

    /**
     * 传入Section，理论上这个时候每个Section已经bind了数据。
     */
    fun onStart(context: Context, array: List<Section>) {
        val recycler = mRecyclerView ?: return
        val adapter = SectionAdapter()
        adapter.setSections(array)
        val layout = SectionLayoutManager.Builder()
            .setSectionAdapter(adapter)
            .build(context)
        recycler.layoutManager = layout
        recycler.adapter = adapter
        mAdapter = adapter
        mLayoutManager = layout
    }

    fun onStop() {}

    fun onDetach() {
        isAttached = false
    }
}