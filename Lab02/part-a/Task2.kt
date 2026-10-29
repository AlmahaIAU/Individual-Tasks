// Lab 2 - Task 2: Functions, default values and named arguments

fun greetStudent(name: String, course: String = "CSC 402"): String {
    return "Welcome to $course, $name!"
}

// Single-expression function: 10% quizzes, 70% project, 20% final exam
fun finalMark(quizzes: Double, project: Double, finalExam: Double): Double =
    quizzes * 0.10 + project * 0.70 + finalExam * 0.20

fun printBanner(title: String, width: Int = 40) {
    println("-".repeat(width))
    println(title)
    println("-".repeat(width))
}

fun task2() {
    printBanner("CSC 402 Lab 2")
    println(greetStudent("Sara"))
    println(greetStudent("Sara", "CSC 311"))

    val positional = finalMark(85.0, 90.0, 78.0)
    val named = finalMark(finalExam = 78.0, quizzes = 85.0, project = 90.0)
    println("Final mark (positional): $positional")
    println("Final mark (named):      $named")
}
