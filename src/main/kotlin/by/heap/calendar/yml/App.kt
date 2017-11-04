package by.heap.calendar.yml

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.nio.file.Paths

/**
 * 1. Read list of events (number of yaml files in specific directory);
 * 2. Process: (List<Path>) -> List<Event>;
 * 3. Transform (List<Event>) -> List<ICalEvent>;
 * 4. Save `calendar.ics` in specific folder.
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val from = args.getOrNull(0) ?: throw RuntimeException("From folder required.")
            val to = args.getOrNull(1) ?: throw RuntimeException("Destination file required.")

            val objectMapper: ObjectMapper = ObjectMapper(YAMLFactory())
                .registerModule(KotlinModule())
                .registerModule(JavaTimeModule())

            YamlToCalendar(
                folderReader = DefaultFolderReader(),
                yamlReader = DefaultYamlReader(objectMapper),
                fileWriter = DefaultFileWriter()
            ).process(Paths.get(from), Paths.get(to))
        } catch (e: Exception) {
            LOGGER.error("Exception occurred", e)
        }
    }

    private val LOGGER = logger<App>()
}
