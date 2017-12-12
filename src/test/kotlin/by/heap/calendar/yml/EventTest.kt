package by.heap.calendar.yml

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

class EventTest {
    @Test
    fun `check that App generates calendar`() {
        App.main(arrayOf("./events", "./build/calendar.ics"))
        assertTrue(Files.exists(Paths.get("./build/calendar.ics")))
    }

    @Test
    fun `test that jackson reads config files properly`() {
        val yamlReader = DefaultYamlReader(DefaultObjectMapperProvider())

        val config = yamlReader.read(Paths.get("./events/config.yml"), CalendarConfig::class)
        assertAll(
            Executable { assertEquals("Event Test Data", config.name) },
            Executable { assertEquals("https://heap.by/", config.url) }
        )
    }

    @Test
    fun `test that jackson reads event files properly`() {
        val yamlReader = DefaultYamlReader(DefaultObjectMapperProvider())
        val zone = ZoneId.of("+03:00")

        val event0 = yamlReader.read(Paths.get("./events/test0.yml"), YamlEvent::class)
        val event0Start = ZonedDateTime.of(
            LocalDateTime.of(2017, Month.DECEMBER, 27, 9, 30),
            zone
        )
        val event0End = ZonedDateTime.of(
            LocalDateTime.of(2017, Month.DECEMBER, 27, 10, 30),
            zone
        )

        val event1 = yamlReader.read(Paths.get("./events/test1.yml"), YamlEvent::class)
        val event1Start = ZonedDateTime.of(
            LocalDateTime.of(1991, Month.DECEMBER, 27, 9, 30),
            zone
        )
        val event1End = ZonedDateTime.of(
            LocalDateTime.of(1991, Month.DECEMBER, 27, 10, 30),
            zone
        )
        assertAll(
            Executable { assertEquals("Test Event 0", event0.name) },
            Executable { assertEquals("Test Event 0 Description", event0.description) },
            Executable { assertEquals("Test Event 0 Location", event0.location) },
            Executable { assertTrue(event0Start.isEqual(event0.start)) },
            Executable { assertTrue(event0End.isEqual(event0.end)) },

            Executable { assertEquals("Test Event 1", event1.name) },
            Executable { assertEquals("Test Event 1 Description", event1.description) },
            Executable { assertEquals("Test Event 1 Location", event1.location) },
            Executable { assertTrue(event1Start.isEqual(event1.start)) },
            Executable { assertTrue(event1End.isEqual(event1.end)) }
        )
    }
}