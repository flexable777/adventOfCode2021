package no.jitk.advent.day12

fun fillConnections(
    lines: List<String>,
    caves: MutableSet<Cave>,
    connections: MutableSet<Connection>
) {
    lines.forEach { line ->
        val split = line.split("-")
        val a = caves.find { it.name == split.first() } ?: throw RuntimeException("rst")
        val b = caves.find { it.name == split.last() } ?: throw RuntimeException("rsrstt")
        connections += Connection(a, b)
    }
}

fun getOther(connection: Connection, cave: Cave): Cave {
    return if (cave == connection.a) connection.b else connection.a
}

fun fillCaves(
    lines: List<String>,
    caves: MutableSet<Cave>
) {
    lines.forEach { line ->
        val split = line.split("-")
        split.forEach {
            caves += Cave(it, it.isUppercase())
        }
    }
}

fun String.isUppercase() = this == this.uppercase()

data class Cave(val name: String, val isBig: Boolean, var visited: Boolean = false) {
    fun isEnd() = name == "end"
    fun isStart() = name == "start"
}

data class Connection(val a: Cave, val b: Cave)