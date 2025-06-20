package com.amonteiro.a2025_04_sdv_rennes_b.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amonteiro.a2025_04_sdv_rennes_b.model.PictureBean
import com.amonteiro.a2025_04_sdv_rennes_b.model.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun main() {
    val viewModel = MainViewModel()
    viewModel.loadWeathers("")
    while(viewModel.runInProgress.value) {
        println("Attente...")
        Thread.sleep(1000)
    }
    //Affichage de la liste (qui doit être remplie) contenue dans la donnée observable
    println("List : ${viewModel.dataList.value}")
    println("ErrorMessage : ${viewModel.errorMessage.value}" )
}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<PictureBean>())
    val runInProgress = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")

//    init {
//        println("init viewmodel")
//        loadFakeData()
//    }

    fun loadWeathers(cityName: String) {

        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val list = WeatherRepository.loadWeathers(cityName)
                dataList.value = list.map { weather ->
                    PictureBean(
                        id = weather.id,
                        url = weather.weather.firstOrNull()?.icon ?: "",
                        title = weather.name,
                        longText = """
            Il fait ${weather.main.temp}° à ${weather.name} (id=${weather.id}) avec un vent de ${weather.wind.speed} m/s
            -Description : ${weather.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${weather.weather.firstOrNull()?.icon ?: "-"}
        """.trimIndent()
                    )
                }
            }
            catch(e:Exception) {
                e.printStackTrace()
                errorMessage.value = e.message ?: "Une erreur est survenue"
            }
            finally {
                runInProgress.value = false
            }
        }

    }

    fun loadFakeData(runInProgress :Boolean = false, errorMessage:String = "" ) {
        this.runInProgress.value = runInProgress
        this.errorMessage.value = errorMessage
        dataList.value = listOf(PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }
}



const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""
