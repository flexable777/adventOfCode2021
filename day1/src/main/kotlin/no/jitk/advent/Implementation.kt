package no.jitk.advent

fun processPart1(lines: List<Int>) =
    lines.windowed(size = 2).count { it[1] > it[0] }

fun processPart2(lines: List<Int>) =
    lines.windowed(size = 3).windowed(size = 2)
        .count { it.last().sum() > it.first().sum() }