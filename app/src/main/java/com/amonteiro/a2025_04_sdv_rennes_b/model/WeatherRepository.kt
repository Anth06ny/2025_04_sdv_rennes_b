package com.amonteiro.a2025_04_sdv_rennes_b.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


//Utilisation
fun main() {
//    //Lance la requête et met le corps de la réponse dans html
//    var html : String = WeatherRepository.sendGet("https://www.google.fr")
//    //Affiche l'HTML
//    println("html=$html")
//
//    WeatherRepository.loadWeathers("Nice")

//    val user = WeatherRepository.loadRandomUser()
//
//    println("""
//        Il s'appelle ${user.name} pour le contacter :
//        Phone : ${user.coord?.phone ?: "-"}
//        Mail : ${user.coord?.mail ?: "-"}
//    """.trimIndent())
//
//
//    for(u in WeatherRepository.loadRandomUsers()) {
//
//        println(
//            """
//        Il s'appelle ${u.name} pour le contacter :
//        Phone : ${u.coord?.phone ?: "-"}
//        Mail : ${u.coord?.mail ?: "-"}
//
//    """.trimIndent()
//        )
//    }

    for (weather in WeatherRepository.loadWeathers("nice")) {
        println(
            """
            Il fait ${weather.main.temp}° à ${weather.name} (id=${weather.id}) avec un vent de ${weather.wind.speed} m/s
            -Description : ${weather.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${weather.weather.firstOrNull()?.icon ?: "-"}
        """.trimIndent()
        )
    }

}


object WeatherRepository {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadWeathers(cityname: String): List<WeatherBean> {
        val json = sendGet("https://api.openweathermap.org/data/2.5/find?q=$cityname&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        Thread.sleep(5000)

        //Parser le JSON avec le bon bean et GSON
        //Si c'est un Objet
        val data = gson.fromJson(json, APIWeatherResult::class.java)

        data.list.forEach { weather->
            weather.weather.forEach {
                it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png"
            }
        }

        //Retourner la donnée
        return data.list
    }

    fun loadRandomUser(): UserBean {
        //Réaliser la requête.
        val json: String = sendGet("https://www.amonteiro.fr/api/randomuser")

        //Parser le JSON avec le bon bean et GSON
        //Si c'est un Objet
        val data = gson.fromJson(json, UserBean::class.java)

        //Retourner la donnée
        return data
    }


    fun loadRandomUsers(): Array<UserBean> {
        //Réaliser la requête.
        val json: String = sendGet("https://www.amonteiro.fr/api/randomusers")

        //Parser le JSON avec le bon bean et GSON
        //Si c'est un Objet
        val data = gson.fromJson(json, Array<UserBean>::class.java)

        //Retourner la donnée
        return data
    }


    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Erreur serveur :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

/* -------------------------------- */
// USER
/* -------------------------------- */

data class UserBean(var name: String, var age: Int, var coord: CoordBean?)
data class CoordBean(var phone: String?, var mail: String?)

/* -------------------------------- */
// Weather
/* -------------------------------- */
data class APIWeatherResult(var list: List<WeatherBean>)
data class WeatherBean(var id: Int, var name: String, var wind: Wind, var main: Main, var weather: List<Weather>)
data class Main(var temp: Double)
data class Weather(var description: String, var icon: String)
data class Wind(var speed: Double)