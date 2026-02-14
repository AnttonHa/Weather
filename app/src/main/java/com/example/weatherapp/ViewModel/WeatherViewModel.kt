package com.example.weatherapp.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    data class UiState(
        val searchCity: String = "",
        val resultCity: String = "",
        val temperature: String = "",
        val description: String = "",
        val wind: String = "",
        val country: String = "",
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState> = _uiState

    fun onCityChange(newCity: String) {
        _uiState.value = _uiState.value.copy(searchCity = newCity)
    }

    fun fetchWeather() {
        val city = _uiState.value.searchCity
        if (city.isBlank()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                resultCity = "",
                temperature = "",
                description = "",
                wind = "",
                country = ""
            )

            try {
                val response = RetrofitInstance.weatherApi.getWeather(
                    city = city,
                    apiKey = BuildConfig.OPENWEATHER_API_KEY
                )

                _uiState.value = _uiState.value.copy(
                    resultCity = response.name,
                    temperature = "${response.main.temp} °C",
                    description = response.weather.firstOrNull()?.description ?: "",
                    wind = "${response.wind.speed} m/s",
                    country = response.sys.country,
                    isLoading = false,
                    searchCity = ""
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Virhe: ${e.message}",
                    isLoading = false
                )
            }
        }
    }
}
