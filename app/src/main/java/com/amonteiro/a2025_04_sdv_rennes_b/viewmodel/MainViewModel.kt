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
}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<PictureBean>())
    val runInProgress = MutableStateFlow(false)

    fun loadWeathers(cityName: String) {

        runInProgress.value = true
        viewModelScope.launch(Dispatchers.IO) {
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
            runInProgress.value = false
        }

    }
}