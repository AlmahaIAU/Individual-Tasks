# Lab 7 — Interoperability and One Activity

Android Studio project **Lab07CourseFeedback** · package `sa.edu.iau.cshj.lab07` · minSdk 26.

| Task | File |
|---|---|
| 2 `RatingBar` wrapped with `AndroidView` (`factory` once, `update` on change, `fromUser` check, Logcat tag `Lab07`) | `ui/components/StarRating.kt` |
| 2 Rating Route/Screen with Reset and `rememberSaveable` | `ui/CourseRating.kt` |
| 3 `WebView` inside a screen (`WebViewClient`, `loadUrl` in `factory`) + INTERNET permission | `ui/CampusNews.kt`, `AndroidManifest.xml` |
| 4 One `NavHost`: Home → Rate CSC 402 / Campus news | `CourseApp.kt` |

Single-Activity check: one `<activity>` in the manifest; no `Fragment` and no `findViewById`;
`AndroidView` only in `StarRating` and `CampusWebPage`.
