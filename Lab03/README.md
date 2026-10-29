# Lab 3 — Layouts, Modifiers and Material Design 3

Android Studio project **CSC402 Lab3** · package `edu.iau.cshj.csc402.lab3`.

| Task | File |
|---|---|
| 1 Modifier-accepting `StudentCard` + `Student` data class | `ProfileCard.kt` |
| 2 Card rebuilt with Column / Row / `weight(1f)` / `HorizontalDivider` | `ProfileCard.kt` |
| 3 Avatar with a status badge (Box stacking, `align(BottomEnd)`) | `ProfileCard.kt` → `Avatar` |
| 4 Modifier order puzzle (Chains A, B, C and the two tap targets) | `OrderPuzzle.kt` |
| 5 Colour roles — no `Color(0x…)` in the card | `ProfileCard.kt` |
| 6 Type scale — no raw `sp` in the card | `ProfileCard.kt` |
| 7 Light + Dark (+ 1.5× font) previews | `ProfileCard.kt` |
| 8 `Scaffold` + `TopAppBar` + FAB with `innerPadding` | `ProfileScreen.kt` |
| 9 Theme from Material Theme Builder, dynamic colour **off** | `ui/theme/Color.kt`, `ui/theme/Theme.kt` |
| 10 Challenge — `LazyColumn` of 5 students, header row, `NavigationBar` | `ProfileScreen.kt` |

Source colour used for Task 9: **#00796B (teal)**. Replace it with the colour your team agrees on, if it differs.
To try the modifier puzzle on a device, temporarily call `OrderPuzzle()` in `MainActivity` instead of `ProfileScreen()`.
