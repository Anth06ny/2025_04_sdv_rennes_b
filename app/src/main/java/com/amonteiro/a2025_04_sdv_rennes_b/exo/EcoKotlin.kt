package com.amonteiro.a2025_04_sdv_rennes_b.exo

import com.amonteiro.a2025_04_sdv_rennes_b.PRICE_BAGUETTE
import com.amonteiro.a2025_04_sdv_rennes_b.PRICE_CROISSANT
import com.amonteiro.a2025_04_sdv_rennes_b.PRICE_SANDWITCH

fun main() {

    val res = boulangerie(nbSand = 5)
    println("res=$res")
    var toto : String?  = null

}


fun boulangerie(nbCroi: Int = 0, nbBag: Int = 0, nbSand: Int = 0) = PRICE_CROISSANT * nbCroi + PRICE_BAGUETTE * nbBag + PRICE_SANDWITCH * nbSand


fun String?.myIsNullOrBlank(): Boolean {
    return this == null || this.isNotBlank()
}