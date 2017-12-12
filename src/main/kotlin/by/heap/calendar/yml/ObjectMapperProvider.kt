package by.heap.calendar.yml

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

/**
 * Provide instance of ObjectMapper.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.5
 */
interface ObjectMapperProvider {
    fun get(): ObjectMapper
}

class DefaultObjectMapperProvider : ObjectMapperProvider {
    private val objectMapper by lazy {
        ObjectMapper(YAMLFactory())
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
    }

    override fun get(): ObjectMapper {
        return objectMapper
    }
}