# Study Planner — Individual Semester App

Android Studio project **StudyPlanner** · package `sa.edu.iau.cshj.studyplanner` · Kotlin · Compose · minSdk 26.
A single-user app that keeps track of study tasks for my own courses.

| Part | Lab | What was added | Tag |
|---|---|---|---|
| 1 | Lab 4 | `PlannerUiState`, stateless `PlannerHomeScreen`, two previews, `PlannerHomeRoute` that owns the state | `semester-app-part1` |
| 2 | Lab 5 | Task detail screen, Navigation Compose (`PlannerHome`, `TaskDetail`), share Intent | `semester-app-part2` |
| 3 | Lab 6 | `rememberSaveable` with a `Saver` so state survives rotation; text-field state hoisted into the Route | `semester-app-part3` |
| 4 | Lab 7 | `StudyTask` model, reusable stateless `TaskItem`, `LazyColumn` with keys, progress computed from the list | `semester-app-part4` |

## Structure (Part 4)

```
app/src/main/java/sa/edu/iau/cshj/studyplanner/
  MainActivity.kt                 the one Activity -> setContent { StudyPlannerApp() }
  model/StudyTask.kt              data class StudyTask(id, title, course, isDone)
  navigation/Routes.kt            @Serializable PlannerHome, TaskDetail(taskId, title)
  navigation/StudyPlannerApp.kt   NavHost - the only place that touches navController
  ui/PlannerHomeScreen.kt         PlannerUiState + Saver, PlannerHomeScreen, PlannerHomeRoute
  ui/TaskItem.kt                  stateless row: Checkbox + title + course
  ui/TaskDetailScreen.kt          TaskDetailScreen + TaskDetailRoute (ACTION_SEND share)
```

## State lists (from the paper sketches)

**Home screen** — shows: greeting, progress line (`x of y done`), new-task field, the task list.
Changes while running: the tasks and their done flags, the new-task text. Events: `onTaskClick`, `onDoneChange`,
`onNewTaskTitleChange`, `onAddTaskClick`.

**Task detail** — shows: task number and title. Needs to open: the task's id (and title). Events: `onShareClick`, `onBackClick`.

**Task row** — shows: checkbox, title, course. Changes: `isDone`. Events: `onTaskClick(id)`, `onDoneChange(id, checked)`.
