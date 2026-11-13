# TravelEase 🚆

**An Adaptive Public Transportation Assistance Prototype**

TravelEase is a Kotlin Multiplatform mobile application prototype developed as part of a university Fuzzy Systems course.

The project explores how fuzzy logic can be used to provide personalized assistance to people navigating public transportation in unfamiliar environments.

## 🎯 Project Concept

The goal is to help users overcome language barriers and understand public transportation through simple, adaptive and accessible guidance.

The application aims to provide:
- Translation assistance.
- Public transportation symbol explanations.
- Step-by-step travel guidance.
- Personalized assistance based on user needs.

## 🧠 Fuzzy Logic

The prototype integrates a fuzzy inference system that determines the appropriate level of assistance based on two inputs:

- **Difficulty:** How difficult the situation is for the user (0–10).
- **Time Pressure:** How much urgency the user experiences (0–10).

The system calculates an assistance score using a precomputed lookup table and bilinear interpolation.

Three assistance levels are defined:

| Level | Score | Guidance |
|---|---|---|
| Low | Below 0.35 | Simple hints |
| Moderate | 0.35–0.65 | Step-by-step checklist |
| High | Above 0.65 | Detailed walkthrough |

## 📱 Current Prototype

The currently implemented user journey focuses on purchasing a train ticket:

1. Select public transportation.
2. Choose "Buy a train ticket".
3. Complete the adaptive assistance questionnaire.
4. Receive low, moderate or high assistance.
5. Access a translated German phrase for purchasing a ticket.

Additional scenarios and features are part of the planned development.

## 🛠️ Technology Stack

- Kotlin Multiplatform (KMP)
- Compose Multiplatform
- Material 3
- Kotlin Coroutines & StateFlow
- Fuzzy Logic

## 🚀 Getting Started

Clone the repository:

```bash
git clone https://github.com/AndrianaSm/TravelEase.git
cd TravelEase
```

Build the Android application:

```bash
./gradlew :androidApp:assembleDebug
```

Run shared tests:

```bash
./gradlew :shared:allTests
```

For iOS, open `iosApp/TravelEase.xcodeproj` in Xcode.

## 📌 Project Status

<badge>Prototype — Work in Progress</badge>

The train-ticket assistance flow is currently implemented. Other transportation scenarios, translations and additional features are planned for future development.

**Repository:** [TravelEase](https://github.com/AndrianaSm/TravelEase)

**Fuzzy Logic Reference:** [ioctl/travelease](https://github.com/ioctl/travelease)