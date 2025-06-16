package com.amonteiro.a2025_04_sdv_rennes_b.exo

import com.amonteiro.a2025_04_sdv_rennes_b.model.CarBean

class MyLiveData(data: CarBean) {

    var data: CarBean = data
        set(value) {
            field = value
            action(value)
        }


    var action: (CarBean) -> Unit = {}
}

fun main() {


    //exo1()

    var toto = MyLiveData(CarBean(marque = "Seat", model = "coucou"))

    toto.action = {
        println(it)
    }

    toto.data.marque = "Audi"
    toto.data = toto.data.copy(marque = "Audi")

}


data class PersonBean(var name: String, var note: Int)

fun exo3() {
    val list = arrayListOf(
        PersonBean("toto", 16),
        PersonBean("Tata", 20),
        PersonBean("Toto", 8),
        PersonBean("Charles", 14)
    )

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >= 10 }.joinToString("\n") { "-${it.name} : ${it.note}" })

    //TODO
    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    println(list.count { it.name.equals("toto", true) })

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.count { it.name.equals("toto", true) && it.note >= 10 })

    var avg = list.map { it.note }.average()
    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    println(list.count { it.name.equals("toto", true) && it.note >= avg })

    println("\n\nAfficher la list triée par nom sans doublon")
    list.distinctBy { it.name }.sortedBy { it.name }.joinToString { it.name }

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.filter { it.note < 10 }.forEach { it.note++ }

    println("\n\nAjouter un point à tous les Toto")
    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")

    //TODO Créer une variable isToto contenant une lambda qui teste si un PersonBean s'appelle Toto

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")
}

fun exo1() {
    //Déclaration
    val lower: (String) -> Unit = { it: String -> println(it.lowercase()) }
    val lower2 = { it: String -> println(it.lowercase()) }
    val lower3: (String) -> Unit = { it -> println(it.lowercase()) }
    val lower4: (String) -> Unit = { println(it.lowercase()) }

    //Appel
    lower("Coucou")
//    hour : prenant un nombre de minutes et retournant le nombre d’heures équivalentes
    val hour: (Int) -> Int = { it / 60 }
    println(hour(125))
//    max : prenant 2 entiers et retournant le plus grand (Math.max())
    val max = { a: Int, b: Int -> Math.max(a, b) }
    println(max(5, 6))

//    reverse : retourne le texte à l’envers toto -> otot (.reversed())
    val reverse: (String) -> Unit = { it.reversed() }

    var minToMinHour: ((Int?) -> Pair<Int, Int>?)? = { if (it == null) null else Pair(it / 60, it % 60) }
    val minToMinHour2: (Int?) -> Pair<Int, Int>? = { it?.let { Pair(it / 60, it % 60) } }

    println(minToMinHour?.invoke(123))
    println(minToMinHour?.invoke(null))

    minToMinHour = null

}