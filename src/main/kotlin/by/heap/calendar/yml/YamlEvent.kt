package by.heap.calendar.yml

import biweekly.component.VEvent
import biweekly.property.Status
import biweekly.property.Transparency
import java.time.ZonedDateTime
import java.util.Date

/**
 * Represents event parsed from yaml.
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
data class YamlEvent(
    val name: String,
    val start: ZonedDateTime,
    val end: ZonedDateTime,
    val description: String,
    val location: String
)

/**
 * Convers an [YamlEvent] to [VEvent].
 *
 * @author Ibragimov Ruslan
 * @since 0.0.3
 */
fun YamlEvent.toCalendarEvent(): VEvent {
    return VEvent().also { e ->
        e.setUid((name + start.toInstant()).toBase64().trimEnd('='))
        e.setSummary(name)
        e.setDateStart(Date.from(start.toInstant()))
        e.setDateEnd(Date.from(end.toInstant()))
        e.setDescription(description)
        e.setLocation(location)
        e.setStatus(Status.confirmed())
        e.setTransparency(Transparency.opaque())
    }
}