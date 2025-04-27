# AI Safety Incident Tracker - Android App

## Overview
This is a simple **Android (Kotlin)** app built for the HumanChain assessment.  
The app allows users to **view**, **filter**, **add**, and **report** hypothetical AI safety incidents, focusing on user interaction, state management, and clean UI.

It also includes:
- A **Splash Screen** on app start.
- **Signup/Login** flow using **SharedPreferences** to manage simple user sessions.

---

## Features
- 🔥 Splash screen with smooth transition to login/signup.
- 🧑 User Signup/Login (SharedPreferences for local session management).
- 📜 Scrollable list of AI Safety Incidents (RecyclerView + CardView).
- 🔎 Filter incidents based on **Severity** (using Spinner).
- 📄 Detailed view of each Incident with Title, Severity, Date, and Description.
- ➕ "Report Incident" screen with form input for Title, Description, and Severity selection.
- ✅ New incident saves locally and updates the list immediately.
- 📅 Reported Date is automatically generated using the current time.
- 🖌️ Clean, standard Android layouts and styling.

---

## Tech Stack
- Kotlin
- Android SDK
- SharedPreferences (for authentication)
- RecyclerView & CardView (for incident list)
- Spinner (for filtering)
- Material Components (for FAB, EditText, Buttons)

---

## How to Build & Run Locally

1. **Clone the Repository**
   git clone https://github.com/your-username/ai-safety-incident-tracker.git
   
3. **Open in Android Studio**
   Open Android Studio

   Click on Open Project and select the cloned folder.

4. **Build & Run**

   Connect an Android device or use an emulator.

   Click on the Run button (green arrow) in Android Studio.

