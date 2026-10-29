// Lab 2 - Task 1: Variables, types and string templates

fun task1() {
    val university = "Imam Abdulrahman Bin Faisal University" // never changes -> val
    val courseCode = "CSC 402"                                // never changes -> val
    val creditHours = 3                                       // never changes -> val
    var studentsEnrolled = 28                                 // students may add/drop -> var
    val isElective = false                                    // never changes -> val

    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")

    studentsEnrolled += 2
    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")

    println("University name length: ${university.length}")
    println("Is elective: $isElective")

    // courseCode = "CSC 403"
    // Compiler error when uncommented:  "Val cannot be reassigned"
}
