# TravelEase 🚆

**An Adaptive Public Transportation Assistance Prototype**

TravelEase is a Kotlin Multiplatform mobile application prototype developed for a university Fuzzy Systems course. It explores how fuzzy logic can adapt the amount of guidance provided to users navigating public transportation in unfamiliar environments.

## 🎯 Project Concept

TravelEase aims to make public transportation easier to navigate by combining:
- Adaptive assistance based on the user's perceived difficulty and time pressure.
- German phrases with English translations.
- Guidance for purchasing train tickets.
- Information about public transportation symbols.

The current adaptive journey focuses on one main scenario: **buying a train ticket**. Additional transportation scenarios are planned for future development.

## 🧠 Adaptive Assistance with Fuzzy Logic

The assistance level is determined by two user inputs, each rated from **0 to 10**:

- **Difficulty:** How difficult the situation feels to the user.
- **Time Pressure:** How urgent the situation feels to the user.

The fuzzy system uses a precomputed lookup table and bilinear interpolation to calculate an assistance score.

Three assistance levels are available:

| Assistance Level | Score | User Experience |
|---|---|---|
| **Low** | Below 0.35 | Minimal help: an essential German phrase, English translation, written pronunciation guidance, and a short tip. |
| **Moderate** | 0.35–0.65 | Guided overview: ticket information and a numbered checklist explaining what the user needs to do. |
| **High** | Above 0.65 | Interactive guidance: a detailed four-step walkthrough with explanations, useful German phrases, a progress indicator, and Previous/Next navigation. |

Each level provides a different amount of support:

- **Low:** Gives the user a little help to get started.
- **Moderate:** Explains what the user needs to do.
- **High:** Guides the user through the process one step at a time.

## 📱 Current Prototype

The main adaptive user journey focuses on purchasing a train ticket:

1. Select public transportation.
2. Choose **Buy a train ticket**.
3. Complete the adaptive assistance questionnaire.
4. Receive a Low, Moderate, or High assistance experience.
5. Follow the guidance for purchasing a train ticket.
6. View useful German phrases and their English translations.

### Assistance Experiences

**Low Assistance — "You can do this!"**

Provides minimal support with a useful German phrase, its English translation, written pronunciation guidance, and a short practical tip.

**Moderate Assistance — "Let's do it together!"**

Provides a structured overview of the ticket-purchasing process, including the destination, ticket selection, numbered instructions, and useful German phrases.

**High Assistance — "We'll guide you through every step."**

Provides a more interactive experience by dividing the process into four individual steps:

1. Find a ticket machine.
2. Choose the destination.
3. Choose the ticket type.
4. Pay and collect the ticket.

Users can navigate between steps, follow the progress indicator, and receive explanations and relevant German phrases along the way.

## 🛠️ Technology Stack

- Kotlin Multiplatform (KMP)
- Compose Multiplatform
- Material 3
- Kotlin Coroutines and StateFlow
- Fuzzy Logic

## 🚀 Getting Started

Clone the repository:

\`\`\`bash
git clone https://github.com/AndrianaSm/TravelEase.git
cd TravelEase
\`\`\`

Build the Android application:

\`\`\`bash
./gradlew :androidApp:assembleDebug
\`\`\`

Run shared tests:

\`\`\`bash
./gradlew :shared:allTests
\`\`\`

For iOS development, open the iOS project in Xcode.

## 📌 Project Status

**University course prototype**

The train-ticket purchasing scenario and its three adaptive assistance experiences are implemented in the current prototype. Additional transportation scenarios and features can be explored in future development.

Audio pronunciation is not implemented. German phrases are presented as text with English translations and written pronunciation guidance where available.

## 🔗 References

- **Mobile Application:** [AndrianaSm/TravelEase](https://github.com/AndrianaSm/TravelEase)
- **Fuzzy Logic Reference:** [ioctl/travelease](https://github.com/ioctl/travelease)