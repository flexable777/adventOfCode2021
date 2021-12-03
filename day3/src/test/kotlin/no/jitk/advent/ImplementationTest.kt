package no.jitk.advent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class Implementation3Test {

    @Test
    fun `original test part 1`() {
        val lines = File("src/test/resources/test_data.txt").readLines()

        assertThat(Implementation3().processPart1(lines)).isEqualTo(198)
    }

    @Test
    fun `actual run part 1`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + Implementation3().processPart1(lines))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `original test part 2`() {
        val lines = File("src/test/resources/test_data.txt").readLines()

        assertThat(Implementation3().processPart2(lines)).isEqualTo(230)
    }

    @Test
    fun `actual run part 2`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 2: " + Implementation3().processPart2(lines))
        }
        println("part 2 took $millis millis to run")
    }

}