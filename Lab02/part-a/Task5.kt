// Lab 2 - Task 5: Data classes and collections

data class Course(val code: String, val title: String, val credits: Int, val days: String)

fun findCourse(list: List<Course>, code: String): String =
    list.firstOrNull { it.code == code }?.title ?: "Course not found"

fun task5() {
    val courses = listOf(
        Course("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
        Course("CSC 311", "Database Systems", 4, "Sun / Tue"),
        Course("CSC 340", "Operating Systems", 3, "Mon / Wed"),
        Course("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
        Course("ENG 214", "Technical Writing", 2, "Tue")
    )

    println("All courses:")
    courses.forEach { println(it) }

    println("\nCourses with 3 credits or more:")
    courses.filter { it.credits >= 3 }.forEach { println(it) }

    println("\nCourse codes:")
    println(courses.map { it.code })

    println("\nTotal credit hours: ${courses.sumOf { it.credits }}")

    println("\nfindCourse(\"CSC 402\") -> ${findCourse(courses, "CSC 402")}")
    println("findCourse(\"CSC 999\") -> ${findCourse(courses, "CSC 999")}")

    val csc402FourCredits = courses[0].copy(credits = 4)
    println("\nCopy with 4 credits: $csc402FourCredits")
    println("Original in list:    ${courses[0]}")
}
