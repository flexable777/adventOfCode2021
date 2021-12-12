package no.jitk.advent.day12

val pathsPart1 = mutableSetOf<List<Cave>>()

fun processDay12Part1(lines: List<String>): Int {
    val caves = mutableSetOf<Cave>()
    val connections = mutableSetOf<Connection>()
    fillCaves(lines, caves)
    fillConnections(lines, caves, connections)

    connections.filter { it.a.isStart() || it.b.isStart() }.forEach { connection ->
        if (connection.a.isStart()) {
            visitDay1(connection.b, connections, caves, mutableListOf(connection.a))
        } else {
            visitDay1(connection.a, connections, caves, mutableListOf(connection.b))
        }
    }

    return pathsPart1.size
}

fun visitDay1(
    cave: Cave,
    connections: MutableSet<Connection>,
    caves: MutableSet<Cave>,
    travels: MutableList<Cave>
) {
    if (!cave.isBig && cave in travels) {
        return
    }
    travels += cave

    if (cave.isEnd()) {
        pathsPart1 += travels.toMutableList()
    }

    connections.filter { cave == it.a || cave == it.b }.forEach { connection ->
        val caveToVisitNext = getOther(connection, cave)
        visitDay1(caveToVisitNext, connections, caves, travels.toMutableList())
    }

    travels.clear()
}