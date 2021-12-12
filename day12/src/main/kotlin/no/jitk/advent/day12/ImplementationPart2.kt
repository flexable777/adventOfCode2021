package no.jitk.advent.day12

val pathsPart2 = mutableSetOf<List<Cave>>()

fun processDay12Part2(lines: List<String>): Int {
    val caves = mutableSetOf<Cave>()
    val connections = mutableSetOf<Connection>()
    fillCaves(lines, caves)
    fillConnections(lines, caves, connections)

    connections.filter { it.a.isStart() || it.b.isStart() }.forEach { connection ->
        if (connection.a.isStart()) {
            visitDay2(connection.b, connections, caves, mutableListOf(connection.a))
        } else {
            visitDay2(connection.a, connections, caves, mutableListOf(connection.b))
        }
    }

    return pathsPart2.size
}

fun visitDay2(
    cave: Cave,
    connections: MutableSet<Connection>,
    caves: MutableSet<Cave>,
    travels: MutableList<Cave>
) {
    val smallWithTwo =
        travels.filter { !it.isBig && it.name !in listOf("start", "end") }.groupBy { it }.filter { it.value.size > 1 }
    val cavePreviously = travels.filter { !it.isBig && it == cave && it.name !in listOf("start", "end") }
    if (cave.isStart() ||
        cavePreviously.isNotEmpty() && smallWithTwo.size == 1
    ) {
        return
    }
    travels += cave

    if (cave.isEnd()) {
        pathsPart2 += travels
        return
    }

    connections.filter { cave == it.a || cave == it.b }.forEach { connection ->
        val caveToVisitNext = getOther(connection, cave)
        visitDay2(caveToVisitNext, connections, caves, travels.toMutableList())
    }

    travels.clear()
}