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

    fun version(data: String): String? {
        try {
            val regex = Regex("\\d{6}(\\d{2})\\w+", RegexOption.IGNORE_CASE)
            val matches = regex.findAll(data).toList()
            if (matches.isEmpty()) return null
            val firstMatch = matches.first()
            return firstMatch.groups[1]?.value
        } catch (_: Exception) {
            return null
        }
    }
}
