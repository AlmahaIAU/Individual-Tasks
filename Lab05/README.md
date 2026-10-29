# Lab 5 — Intents and Navigation

Android Studio project **Lab05Courses** · package `sa.edu.iau.cshj.lab05` · minSdk 26 ·
Navigation Compose 2.10.2 + Kotlin serialization plugin.

- `ui/CourseScreens.kt` — starter screens, `HomeRoute` with `ACTION_VIEW` (website) and `ACTION_DIAL` (dialer) inside
  `try / catch`, and the `ACTION_SEND` share sheet in `CourseDetailRoute`.
- `navigation/Routes.kt` — `Home`, `CourseList` (objects) and `CourseDetail(code)` (data class), all `@Serializable`.
- `navigation/CourseApp.kt` — `NavHost`, `navigate(CourseDetail(code))`, `toRoute()`, `popBackStack()`.
