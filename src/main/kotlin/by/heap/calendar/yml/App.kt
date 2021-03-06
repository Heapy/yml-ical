package by.heap.calendar.yml

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

            YamlToCalendar(
                folderReader = DefaultFolderReader(),
                yamlReader = DefaultYamlReader(DefaultObjectMapperProvider()),
                fileWriter = DefaultFileWriter()
            ).process(Paths.get(from), Paths.get(to))
        } catch (e: Exception) {
            LOGGER.error("Exception occurred", e)
            Runtime.getRuntime().exit(1)
        }
    }

    private val LOGGER = logger<App>()
}
