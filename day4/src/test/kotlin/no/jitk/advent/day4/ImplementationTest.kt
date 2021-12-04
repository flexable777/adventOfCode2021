package no.jitk.advent.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `original test part 1`() {
        val lines = File("src/test/resources/test_data.txt").readLines().toMutableList()

        assertThat(processDay4Part1(lines)).isEqualTo(4512)
    }

    @Test
    fun `actual run part 1`() {
        val lines = File("src/test/resources/actual_data.txt").readLines().toMutableList()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + processDay4Part1(lines))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `original test part 2`() {
        val lines = File("src/test/resources/test_data.txt").readLines().toMutableList()

        assertThat(processDay4Part2(lines)).isEqualTo(1924)
    }

    @Test
    fun `actual run part 2`() {
        val lines = File("src/test/resources/actual_data.txt").readLines().toMutableList()

        val millis = measureTimeMillis {
            println("Actual run part 2: " + processDay4Part2(lines))
        }
        println("part 2 took $millis millis to run")
    }

}