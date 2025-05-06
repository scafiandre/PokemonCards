# PokemonCards

PokemonCards is an Android app that displays a list of Pokémon fetched from the PokéAPI. Users can browse, view details, and mark favorites. The app uses Jetpack Compose for the UI and modern Android architecture components.

## Features

- 📱 Browse a list of Pokémon with infinite scroll (paging)
- ⭐ Mark/unmark Pokémon as favorites
- 🔍 View detailed information of each Pokémon
- 🌙 Light and dark theme support

## Screenshots

> *(Add screenshots here to showcase your app in action)*

## Tech Stack

- **Kotlin**
- **Jetpack Compose** – Declarative UI framework
- **Android Architecture Components**
  - ViewModel
  - LiveData / StateFlow
- **Paging 3** – For infinite scroll/pagination
- **Hilt** – Dependency Injection
- **Retrofit** – Network layer
- **Coil** – Image loading
- **Mockito / Mockito-Kotlin** – Unit testing

## Setup

1. Clone the repository:

```bash
git clone https://github.com/scafiandre/PokemonCards.git
```
2. Open in Android Studio.

3. Sync Gradle.

4. Run the app on an emulator or physical device.

## API
This project uses PokéAPI as its data source.

## License
MIT

Made with ❤️ by @scafiandre
