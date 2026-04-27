# GIF App

A modern Android sample app that displays trending GIFs from the Giphy API using a glassmorphism-inspired Jetpack Compose UI.

The project is structured around Clean Architecture, MVVM with an MVI-inspired event/state flow, and a modular setup with a Kotlin-only `core` module.

## Features

- Trending GIF feed powered by the Giphy API
- Glassmorphism-based UI inspired by premium mobile product cards
- Custom light and dark themes
- Settings drawer with theme switching
- Animated favorite interaction
- Paging support for infinite scrolling
- Reusable Compose components with previews
- Navigation 3 host setup

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Navigation 3
- Paging 3
- ViewModel
- StateFlow / SharedFlow
- Hilt
- Retrofit
- OkHttp
- Coil

## Architecture

The project follows a layered architecture with clear separation of responsibilities:

- `core`
  Pure Kotlin domain module.
  Contains domain models, repository contracts, and use cases.

- `app/data`
  Infrastructure and data layer.
  Handles API access, DTOs, mappers, paging, remote data source, and repository implementation.

- `app/presentation`
  UI layer.
  Contains screens, components, navigation, ViewModel, UI state, and UI events.

### Current flow

`UI -> HomeUiEvent -> HomeViewModel -> HomeUiState / SharedFlow events -> Compose screen`

For GIF loading:

`UI -> ViewModel -> GetTrendingGifsUseCase -> GifRepository -> GifRepositoryImpl -> GifRemoteDataSource -> GifApi`

## UI Pattern

The presentation layer uses:

- `HomeUiState`
  Persistent screen state exposed as `StateFlow`

- `HomeUiEvent`
  User interactions and ephemeral UI events

- `HomeViewModel`
  Receives UI events, updates screen state, and emits transient feedback events such as snackbar messages

This keeps the screen predictable, testable, and scalable without pushing business logic into composables.

## Setup

### 1. Clone the project

```bash
git clone <your-repo-url>
cd gif-app
```

### 2. Add your Giphy API key

Create a file named `apikey.properties` in the project root:

```properties
API_KEY=your_giphy_api_key
```

The app reads this value through `BuildConfig.API_KEY`.

### 3. Build or run

```bash
./gradlew assembleDebug
```

or open the project in Android Studio and run the `app` module.

## Notes

- The `core` module contains no Android framework code.
- The app currently uses a remote-only source for GIFs.
- Room dependencies exist in the project, but local persistence is not implemented yet.
- Component previews are available for the main UI building blocks.

## Design Direction

The current UI is intentionally styled around:

- dark premium surfaces
- blue/mint highlights
- glassmorphism containers

This design system is reusable through shared components such as `GlassSurface`.


## License

This project is licensed under the MIT License.
