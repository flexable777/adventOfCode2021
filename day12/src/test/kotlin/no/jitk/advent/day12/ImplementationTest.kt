package no.jitk.advent.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `test part 1`() {
        val lines = File("src/test/resources/test_data.txt").readLines()

        assertThat(processDay12Part1(lines)).isEqualTo(226)
    }

    @Test
    fun `actual run part 1`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + processDay12Part1(lines))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `test part 2`() {
        val lines = File("src/test/resources/test_data.txt").readLines()

        assertThat(processDay12Part2(lines)).isEqualTo(3509)
    }

    @Test
    fun `actual run part 2`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 2: " + processDay12Part2(lines))
        }
        println("part 2 took $millis millis to run")

    }

}