package by.heap.calendar.yml

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * Writed calendar to specified file.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
interface FileWriter {
    fun write(path: Path, data: String)
}

/**
 * [FileWriter]
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
class DefaultFileWriter : FileWriter {
    override fun write(path: Path, data: String) {
        Files.write(
            path,
            data.toByteArray(charset = Charsets.UTF_8),
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        )
    }
}