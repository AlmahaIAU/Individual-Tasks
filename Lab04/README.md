# Lab 4 — Activities, Composables and State

Android Studio project **Lab04Attendance** · package `sa.edu.iau.cshj.lab04` · minSdk 26.

- `ui/AttendanceScreen.kt` — `AttendanceUiState` (Task 2), stateless `AttendanceScreen` + two previews (Task 3),
  `AttendanceRoute` that owns the state and uses `copy` (Task 4).
- `MainActivity.kt` — `onCreate`: `super.onCreate`, `enableEdgeToEdge`, `setContent { … AttendanceRoute() }`.
- The launcher experiment (Task 1.4) and the frozen-lambda experiment (Task 4.5) are reverted; the broken version is
  kept as a comment in `AttendanceRoute`.
