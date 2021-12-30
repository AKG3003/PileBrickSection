package com.murphy.pilebricksection.entity

import java.util.ArrayList

/**
 * Murphy
 */
object DataUtil {

    fun createNewData(): SectionBean {
        val bean = SectionBean()
        val a = SectionBean.SectionA()
        a.saText = "sectionA"
        val bL = ArrayList<SectionBean.SectionB>()
        for (i in 0..10) {
            bL.add(SectionBean.SectionB())
            bL[i].sbText = "sectionB:num$i"
        }
        val caL = ArrayList<SectionBean.SectionCA>()
        val cbL = ArrayList<SectionBean.SectionCB>()
        for (i in 0..10) {
            caL.add(SectionBean.SectionCA())
            caL[i].scaText = "sectionCA:num$i"
            caL[i].type = i % 2
        }
        for (i in 0..10) {
            cbL.add(SectionBean.SectionCB())
            cbL[i].scbText = "sectionCB:num$i"
        }
        val c = SectionBean.SectionC()
        c.sCAs = caL
        c.sCBs = cbL

        bean.sA = a
        bean.sBs = bL
        bean.sC = c
        return bean
    }
}