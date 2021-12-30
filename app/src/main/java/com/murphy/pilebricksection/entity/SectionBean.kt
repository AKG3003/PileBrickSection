package com.murphy.pilebricksection.entity

class SectionBean {
    var sA: SectionA? = null

    var sBs: List<SectionB>? = null

    var sC: SectionC? = null

    class SectionA {
        var saText: String? = null
    }

    class SectionB {
        var sbText: String? = null
    }

    class SectionC {
        var sCAs: List<SectionCA>? = null
        var sCBs: List<SectionCB>? = null
    }

    class SectionCA {
        var scaText: String? = null
        var type: Int = 0
    }

    class SectionCB {
        var scbText: String? = null
    }
}