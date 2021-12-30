package com.murphy.pilebricksection.madoka

object SectionUtils {
    fun GCD(a: Int, b: Int): Int {
        return if (a % b == 0) b else GCD(b, a % b)
    }

    fun LCM(a: Int, b: Int): Int {
        return a * b / GCD(a, b)
    }
}