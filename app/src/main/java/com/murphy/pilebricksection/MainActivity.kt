package com.murphy.pilebricksection

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.murphy.pilebricksection.entity.DataUtil
import com.murphy.pilebricksection.madoka.Section
import com.murphy.pilebricksection.test.DisplaySectionA
import com.murphy.pilebricksection.test.DisplaySectionC
import com.murphy.pilebricksection.test.SectionGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val group = SectionGroup()
        val root = findViewById<ViewGroup>(R.id.center)
        group.onAttach(root)
        val data = DataUtil.createNewData()
        val list = ArrayList<Section>()
        val sectionA = DisplaySectionA()
        sectionA.bindData(data.sA!!)
        list.add(sectionA)
        val sectionC = DisplaySectionC()
        sectionC.bindData(data.sC!!)
        list.add(sectionC)
        group.onStart(this, list)
    }
}