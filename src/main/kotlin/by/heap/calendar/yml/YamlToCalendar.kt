package by.heap.calendar.yml

import biweekly.Biweekly
import biweekly.ICalendar
import biweekly.property.CalendarScale
import java.nio.file.Path

/**
 * Main class for processing folder to iCalendar.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
class YamlToCalendar(
    private val folderReader: FolderReader,
    private val yamlReader: YamlReader,
    private val fileWriter: FileWriter
) {
    fun process(folder: Path, destination: Path) {
        // get all yml files in folder
        val ymls = folderReader
            .read(folder, ".yml")

        // get configuration from ymls
        val config = ymls
            .filter { it.fileName.toString() == "config.yml" }
            .map { yamlReader.read(it, CalendarConfig::class) }
            .first()

        // get all ymls except configuration
        val events = ymls
            .filter { it.fileName.toString() != "config.yml" }
            .map { yamlReader.read(it, YamlEvent::class) }
            .onEach { LOGGER.info("Event: $it") }

        // create calendar based on configuration and events
        val calendar = events
            .map(YamlEvent::toCalendarEvent)
            .fold(ICalendar(), { ical, event ->
                ical.addEvent(event)
                ical
            })
            .also { ical ->
                ical.setName(config.name)
                ical.setUrl(config.url)
                ical.setCalendarScale(CalendarScale.gregorian())
            }

        fileWriter.write(destination, Biweekly.write(calendar).go())
    }

    companion object {
        private val LOGGER = logger<YamlToCalendar>()
    }
}