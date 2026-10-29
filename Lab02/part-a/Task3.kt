// Lab 2 - Task 3: Decisions with if and when

fun letterGrade(mark: Int): String = when (mark) {
    in 95..100 -> "A+"
    in 90..94 -> "A"
    in 85..89 -> "B+"
    in 80..84 -> "B"
    in 75..79 -> "C+"
    in 70..74 -> "C"
    in 65..69 -> "D+"
    in 60..64 -> "D"
    in 0..59 -> "F"
    else -> "Invalid"
}

fun status(mark: Int): String = if (mark >= 60) "Pass" else "Fail"

fun task3() {
    val marks = listOf(97, 88, 74, 61, 45, 130)
    for (mark in marks) {
        println("Mark $mark -> ${letterGrade(mark)} (${status(mark)})")
    }
}
