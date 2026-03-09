package alfa.system.binreader.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun formatDateStamp(
        millis: Long,
        locale: Locale = Locale.ENGLISH,
        pattern: String = "d MMMM, HH:mm"
    ): String {
        val formatter = SimpleDateFormat(pattern, locale)
        return formatter.format(Date(millis))
    }
}