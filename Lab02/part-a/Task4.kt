// Lab 2 - Task 4: Null safety

fun describeTeam(teamName: String?): String =
    teamName?.let { "Team: $it (${it.length} characters)" } ?: "Team not registered yet"

fun task4() {
    val nickname: String? = null
    val fullName: String = "Abdullah Al-Qahtani"

    println(fullName.length)
    // Safe call: nickname is null, so ?. skips .length and the whole expression is null
    println(nickname?.length)

    // Elvis operator: use the value if it is not null, otherwise the fallback text
    println(nickname ?: "no nickname set")

    println(describeTeam("Team Falcon"))
    println(describeTeam(null))

    // println(nickname!!.length)
    // When uncommented this throws: java.lang.NullPointerException
}
