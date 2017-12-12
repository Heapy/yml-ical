package by.heap.calendar.yml

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
    private val objectMapperProvider: ObjectMapperProvider
) : YamlReader {
    override fun <T : Any> read(path: Path, klass: KClass<T>): T {
        return objectMapperProvider.get().readValue(path.toFile(), klass.java)
    }
}