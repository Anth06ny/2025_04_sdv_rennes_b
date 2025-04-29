package com.amonteiro.a2025_04_sdv_rennes_b.model

import java.util.Random

fun main() {
//    var car = CarBean("seat", "leon")
//    car.color = "res"
//    var car2 = CarBean("seat", "leon")
//    println("car=$car")
//    println("car2=$car2")
//    println("car==car2 : ${car == car2}")
//    println("car===car2 : ${car === car2}")
//    PrintRandomIntBean(18)

//    var t1 = ThermometerBean(min=-20, max= 50, value =0)
//    println("Température de ${t1.value}") // 0
//
//    //Cas qui marche
//    t1.value = 10
//    println("Température de ${t1.value}") // 10 attendu
//
//    //Borne minimum
//    t1.value = -30
//    println("Température de ${t1.value}") // -20 attendu
//
//    //Borne maximum
//    t1.value = 100
//    println("Température de ${t1.value}") // 50 attendu
//
//    //Pour les plus rapides : Cas de départ
//    t1 = ThermometerBean(min=-20, max= 50, value =-100)
//    println("Température de ${t1.value}") // -20 attendu
//
//    ThermometerBean.getCelsiusThermometer()

    val randomName = RandomName()
    randomName.add("bobby")
    randomName.addAll("bobby", "Tobby", "Gustavo")
    repeat(10) {
        println(randomName.next() + " ")
    }
    repeat(10) {
        print(randomName.nextDiff() + " ")
    }
    println("\n ${randomName.next2()}")
}

class RandomName {

    private val list = arrayListOf("bob", "toto", "bobby")
    private var oldValue = ""

    fun add(name: String) =
        if (name.isNotBlank() && name !in list) list.add(name) else false


    fun addAll(vararg names: String) {
        for (name in names) {
            add(name)
        }
    }


    fun next() = list.random()

    fun nextDiff(): String {
        var newValue = next()
        while (newValue == oldValue) {
            newValue = next()
        }

        oldValue = newValue
        return newValue
    }

    fun nextDiff2() = list.filter { it != oldValue }.random().also { oldValue = it }

    fun next2() = Pair(nextDiff(), nextDiff())

}

class ThermometerBean(var min: Int, var max: Int, value: Int) {

    var value: Int = value.coerceIn(min, max)
        set(newValue) {
            field = newValue.coerceIn(min, max)
        }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)
    }
}


class PrintRandomIntBean(val max: Int) {
    private val random: Random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }
}

class HouseBean(var color: String, width: Int, length: Int) {
    var area = width * length

    fun print() = println("La maison $color fait ${area}m²")
}

data class CarBean(var marque: String?, var model: String = "") {
    var color = ""
}