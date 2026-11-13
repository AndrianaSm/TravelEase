TravelEase is a Kotlin Multiplatform project targeting Android and iOS.

The Android application ID and iOS bundle ID are `com.example.travelease`.
They differ from the original starter identifiers, so existing installations
are treated as separate apps and their data is not automatically transferred.

The shared Compose UI implements TravelEase's public-transport task flow. Choose
**Public transport**, then **Buy a train ticket** to answer the assistance
questionnaire and see low, moderate, or high guidance. Each guidance screen
links to the translation; **Understand a transport symbol** opens the
swipeable symbol guide. Other categories and tasks currently show a coming-soon
message. Questionnaire answers and the chosen ticket type are retained while
navigating within the app.

The German and English ticket phrase is sample data. Pronunciation is exposed
through `PronunciationPlayer`, but platform text-to-speech is not yet wired up:
the audio button explains that playback is unavailable. The enlarged phrase
view can be used instead.

* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- iOS app: open [TravelEase.xcodeproj](./iosApp/TravelEase.xcodeproj) in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…