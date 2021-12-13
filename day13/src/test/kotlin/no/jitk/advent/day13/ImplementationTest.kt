package no.jitk.advent.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `original test part 1`() {
        val lines = File("src/test/resources/test_data.txt").readLines()
        //with all folds
        assertThat(processDay13Part1(lines)).isEqualTo(16)
    }

    @Test
    fun `actual run part 1 and 2`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + processDay13Part1(lines))
        }
        println("part 1 took $millis millis to run")
    }

}