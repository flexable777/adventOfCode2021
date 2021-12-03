package no.jitk.advent

fun processDay1Part1(lines: List<Int>) =
    lines.windowed(size = 2).count { it[1] > it[0] }

fun processDay1Part2(lines: List<Int>) =
    lines.windowed(size = 3).windowed(size = 2)
        .count { it.last().sum() > it.first().sum() }