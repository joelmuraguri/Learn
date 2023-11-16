# Quiz App

## Overview

This Android Quiz App is developed using Modern Android Development skills, with a primary focus on state management in Jetpack Compose. The application comprises two screens: StartPage and Quiz Screen. The app leverages two essential libraries, Navigation and Lottie Animation, to enhance the user experience.

## Screens

### StartPage
- The initial screen that welcomes users to the quiz app.
- Provides a starting point for users to initiate the quiz.

### Quiz Screen
- Features a quiz interface where users can progress through questions using the "Next" and "Previous" buttons.
- Utilizes Jetpack Compose for dynamic UI updates, ensuring a seamless and interactive quiz-taking experience.
- Leverages state management to track and update the quiz progress.

## Libraries

### Navigation
- Implements a robust navigation system for seamless transitions between StartPage and Quiz Screen.

### Lottie Animation
- Enhances the app's visual appeal with Lottie animations, providing engaging feedback and transitions.

## State Management in Jetpack Compose

Jetpack Compose relies on a unidirectional data flow, making state management a crucial aspect of creating dynamic and responsive user interfaces. In this project, state management is implemented to track and update the quiz progress, ensuring that the user's interactions with the app are reflected accurately.

### Key Concepts
1. **State Variables:** Jetpack Compose relies on state variables to trigger recomposition when the underlying data changes. In this app, state variables are employed to manage the current quiz question, user responses, and overall quiz progress.

2. **State Hoisting:** To facilitate communication between composables, state variables are hoisted to higher-level composables, ensuring a single source of truth and promoting a clear data flow.

3. **MutableState and ImmutableState:** Jetpack Compose distinguishes between mutable and immutable states. MutableState variables are used for data that can change, such as the current quiz question, while immutable states are employed for data that remains constant throughout the app's lifecycle.

4. **ViewModels:** For more complex state management requirements, ViewModel classes can be utilized to store and manage UI-related data, ensuring data persistence across configuration changes.

## Getting Started

To run the Quiz App on your local machine, follow these steps:

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

Feel free to explore and contribute to the project, enhancing your skills in Modern Android Development and Jetpack Compose!
