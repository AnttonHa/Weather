# Weatherapp

## Retrofit

Retrofit vastaa API-kutsujen tekemisestä OpenWeatherMap-palveluun.

- Muodostaa HTTP-pyynnöt
- Käsittelee vastaukset
- Yhdistyy Gson-muuntimeen JSON-datan käsittelyä varten

---

## JSON, Kotlin dataluokat (Gson)

Gson hoitaa JSON-vastauksen muuntamisen Kotlin-dataluokiksi automaattisesti.

- API palauttaa JSON-muotoista dataa
- Gson muuntaa datan WeatherResponse-olioon
- Retrofit käyttää Gsonia taustalla automaattisesti

---

## Coroutines

API-kutsu suoritetaan coroutinen avulla taustasäikeessä.

- viewModelScope.launch {} käynnistää taustatyön
- Verkko-operaatiot eivät jäädytä käyttöliittymää
- Kun data saapuu, UI päivittyy automaattisesti

---

## UI-tila ja ViewModel

Sovellus käyttää MVVM-mallia:

- ViewModel hallitsee WeatherUiState-oliota
- UI lukee tilaa Composessa
- Kun tila muuttuu Compose renderöi näkymän uudelleen automaattisesti

---

## API-avain

API-avain on tallennettu turvallisesti eikä päädy GitHubiin:

- local.properties: sisältää API-avaimen (gitignored)
- build.gradle.kts: lukee avaimen BuildConfigiin
- BuildConfig.OPENWEATHER_API_KEY: käytetään Retrofit-kutsussa

