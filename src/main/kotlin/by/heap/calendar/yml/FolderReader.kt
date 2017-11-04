package by.heap.calendar.yml

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors.toList

/**
 * Read files with specific extension in folder.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
interface FolderReader {
    fun read(folder: Path, extension: String): List<Path>
}

/**
 * [FolderReader]
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
class DefaultFolderReader : FolderReader {
    override fun read(folder: Path, extension: String): List<Path> {
        return Files
            .list(folder)
            .filter { it.fileName.toString().endsWith(extension) }
            .collect(toList())
    }
}