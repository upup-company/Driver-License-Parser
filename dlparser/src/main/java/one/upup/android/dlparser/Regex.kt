package one.upup.android.dlparser

import java.util.regex.Pattern

object Regex {

    fun firstMatch(pattern: String, data: String): String? {
        try {
            val matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(data)

            if (matcher.find()) {
                val matchedGroup = matcher.group(2)
                if (!matchedGroup.isNullOrEmpty()) {
                    return matchedGroup.trim()
                }
            }
        } catch (e: Exception) {
            // Handle the exception if needed.
        }
        return null
    }

}
