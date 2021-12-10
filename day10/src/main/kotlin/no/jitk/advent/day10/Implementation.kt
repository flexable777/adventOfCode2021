package no.jitk.advent.day10

import java.util.*

fun processDay10Part1(lines: List<String>): Int {
    val bracketPairs = listOf('(' to ')', '{' to '}', '[' to ']', '<' to '>')
    var score = 0

    for (line in lines) {
        val stack = Stack<Char>()
        for (bracket in line.toList()) {
            val bracketPair = bracketPairs.find { it.first == bracket || it.second == bracket }
            if (bracket == bracketPair?.second) {
                if (stack.pop() != bracketPair.first) {
                    when (bracket) {
                        ')' -> score += 3
                        ']' -> score += 57
                        '}' -> score += 1197
                        '>' -> score += 25137
                    }
                    break
                }
            } else {
                stack.push(bracket)
            }
        }
    }
    return score
}

fun processDay10Part2(lines: List<String>): Long {
    val bracketPairs = listOf('(' to ')', '{' to '}', '[' to ']', '<' to '>')
    val completions = mutableListOf<String>()
    for (line in lines) {
        var isUnfinished = true
        val stack = Stack<Char>()
        for (bracket in line.toList()) {
            val bracketPair = bracketPairs.find { it.first == bracket || it.second == bracket }
            if (bracket == bracketPair?.second) {
                if (stack.pop() != bracketPair.first) {
                    isUnfinished = false
                }
            } else {
                stack.push(bracket)
            }
        }
        //Finish line
        if (isUnfinished) {
            var ending = ""
            while (stack.isNotEmpty()) {
                val bracket = stack.pop()
                val bracketPair = bracketPairs.find { it.first == bracket } ?: throw RuntimeException("?")
                ending += bracketPair.second
            }
            completions += listOf(ending)
        }
    }
    val totalScores = mutableListOf<Long>()

    completions.forEach { line ->
        var score = 0L
        line.forEach { c ->
            score *= 5
            when (c) {
                ')' -> score += 1
                ']' -> score += 2
                '}' -> score += 3
                '>' -> score += 4
            }
        }
        totalScores += score
        score = 0
    }
    totalScores.sort()
    return totalScores[totalScores.size / 2]
}