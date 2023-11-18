# ComposeFlix 

## Overview

This is a simple Android movie app designed for learning purposes with a primary focus on testing.
The app utilizes modern Android development principles and employs various Android Jetpack libraries, Compose for UI, 
and third-party libraries for networking, caching, and dependency injection. 
The app emphasizes the importance of testing through unit tests, UI tests, and instrumentation tests.

## Features

### Movie List Screen
- Displays trending films and TV shows.
- Each item card provides an option to mark as a favorite.

### Movie Details Screen
- Shows detailed movie information, including casts and recommended movies.

### Search Screen
- Allows users to search for movies and TV shows.
- Triggered by the Floating Action Button (FAB).

### Profile Screen
- Utilizes DataStore for managing theming, language, name, image, and favorite movie list.

## Libraries Used

- **Compose:** For building the UI screens.
- **Navigation:** For handling navigation between different screens.
- **KSP (Kotlin Symbol Processing):** Used for annotation processing.
- **Kapt:** Annotation processing for data binding and dependency injection.
- **Timber:** Logging library for debugging purposes.
- **Ktor/Retrofit:** For handling network calls.
- **Room:** Used for local caching of movie data.
- **DataStore:** Manages user preferences such as theming, language, name, image, and favorite list.
- **WorkManager:** Schedules background tasks to fetch data every 12 hours and handles data caching and cleanup.
- **Paging:** For efficient loading of large data sets.
- **Firebase:** Integrates log and crash analytics.
- **Coil:** Image loading library for efficient loading and caching of images.
- **Hilt:** Dependency injection library for managing dependencies in the app.

## Testing

The app places a strong emphasis on testing, covering various aspects of the application:

- **Unit Tests:** For components such as the repository and DAO.
- **UI Tests:** Using Espresso for testing the UI components.
- **Instrumentation Tests:** Ensuring the app functions correctly on a device.

Ensure you have the appropriate testing dependencies and configurations set up in Android Studio.

## How to Build and Run

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android emulator or device.

## Contribution Guidelines

Feel free to contribute to the project by creating issues, submitting pull requests, or suggesting improvements.
Please follow the established coding conventions and keep the focus on simplicity for learning purposes.


