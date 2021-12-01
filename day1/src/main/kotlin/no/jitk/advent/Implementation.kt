package no.jitk.advent

fun processPart1(lines: List<Long>) =
    lines.windowed(size = 2).filter { it[1] > it[0] }.size

fun processPart2(lines: List<Long>) =
    lines.map { it.toInt() }.windowed(size = 3).zipWithNext()
        .filter { it.second.sum() > it.first.sum() }.size