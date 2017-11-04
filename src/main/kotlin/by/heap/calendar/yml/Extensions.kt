package by.heap.calendar.yml

import org.slf4j.LoggerFactory
import java.util.Base64

/**
 * Logger creation method.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
internal inline fun <reified T : Any> logger() = LoggerFactory.getLogger(T::class.java)

/**
 * Converts [String] to [Base64] encoded string.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
internal fun String.toBase64(): String = Base64.getUrlEncoder().encodeToString(this.toByteArray(Charsets.UTF_8))