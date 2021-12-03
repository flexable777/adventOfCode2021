package no.jitk.advent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `original test part 1`() {
        val commands = File("src/test/resources/test_data_part1.txt").readLines()
            .map {
                val s = it.split(" ")
                s.first() to s[1].toInt()
            }

        assertThat(Implementation().processDay2Part1(commands)).isEqualTo(150)
    }

    @Test
    fun `actual run part 1`() {
        val commands = File("src/test/resources/actual_data_part1.txt").readLines()
            .map {
                val s = it.split(" ")
                s.first() to s[1].toInt()
            }

        val millis = measureTimeMillis {
            println("Actual run part 1: " + Implementation().processDay2Part1(commands))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `original test part 2`() {
        val commands = File("src/test/resources/test_data_part2.txt").readLines()
            .map {
                val s = it.split(" ")
                s.first() to s[1].toInt()
            }

        assertThat(Implementation().processDay2Part2(commands)).isEqualTo(900)
    }

    @Test
    fun `actual run part 2`() {
        val commands = File("src/test/resources/actual_data_part2.txt").readLines()
            .map {
                val s = it.split(" ")
                s.first() to s[1].toInt()
            }

        val millis = measureTimeMillis {
            println("Actual run part 2: " + Implementation().processDay2Part2(commands))
        }
        println("part 2 took $millis millis to run")
    }

}