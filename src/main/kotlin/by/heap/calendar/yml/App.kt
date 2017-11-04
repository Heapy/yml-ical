package by.heap.calendar.yml

/**
 * 1. Read list of events (number of yaml files in specific directory);
 * 2. Process: (List<Path>) -> List<Event>;
 * 3. Transform (List<Event>) -> List<ICalEvent>;
 * 4. Save `calendar.ics` in specific folder.
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello, World!")
    }
}