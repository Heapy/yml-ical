package by.heap.calendar.yml

import com.fasterxml.jackson.databind.ObjectMapper
import java.nio.file.Path
import kotlin.reflect.KClass

/**
 * Parse yaml files.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
interface YamlReader {
    fun <T : Any> read(path: Path, klass: KClass<T>): T
}

/**
 * [YamlReader]
 */
class DefaultYamlReader(
    private val objectMapper: ObjectMapper
) : YamlReader {
    override fun <T : Any> read(path: Path, klass: KClass<T>): T {
        return objectMapper.readValue(path.toFile(), klass.java)
    }
}