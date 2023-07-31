package one.upup.android.dlparser

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date


// Defines Field Parsing Behavior
public interface FieldParsing {

    // A Field Mapping implementing object
    val fieldMapper: FieldMapping

    // The AAMVA raw barcode data being parsed
    var data: String

    /**
    Parse a string out of the raw data

    - Parameters:
    - The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */
    fun parseString(key: String): String?

    /**
    Parse a double out of the raw data.

    - Parameters:
    - key: The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */
    fun parseDouble(key: String): Double?

    /**
    Parse a date out of the raw data

    - Parameters:
    - key: The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */
    fun parseDate(key: String): Date?

    /**
    Parse the AAMVA expiration date out of the raw data

    - Parameters:
    - key: The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */
    fun parseExpirationDate(): Date?

    /**
    Parse the AAMVA issue date out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseIssueDate(): Date?

    /**
    Parse the AAMVA date of birth out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseDateOfBirth(): Date?

    /**
    Parse the AAMVA issuing country out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseCountry(): IssuingCountry

    /**
    Parse the AAMVA name truncation statuses out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseTruncationStatus(field: String): Truncation

    /**
    Parse the AAMVA gender out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseGender(): Gender

    /**
    Parse the AAMVA eye color out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseEyeColor(): EyeColor

    /**
    Parse the AAMVA name suffix out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseNameSuffix(): NameSuffix

    /**
    Parse the AAMVA height out of the raw data

    - Returns: An optional value parsed out of the raw data in inches
     */
    fun parseHeight(): Double?

    /**
    Parse the AAMVA hair color out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseHairColor(): HairColor

    /**
    The string format used with an DateFormatter to parse dates. Usually 'yyyyMMdd' or 'MMddyyyy'.

    - Returns: An DateFormatter formatter string acceptable date format
     */
    fun getDateFormat(): String

    /**
    Parse the AAMVA first name out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseFirstName(): String?

    /**
    Parse the AAMVA last name out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseLastName(): String?

    /**
    Parse the AAMVA middle name out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    fun parseMiddleName(): String?
}

/**
A basic Field Parsing implementation that can be extended to support multiple AAMVA Versions
 */

open class FieldParser constructor(
    // The raw data from an AAMVA spec adhering PDF-417 barcode
    override var data: String,
    // A Field Mapping object for finding fields in the raw data
    override val fieldMapper: FieldMapper = FieldMapper(),
) : FieldParsing {

    companion object {
        // Used to convert cm to inches for height calculations
        const val INCHES_PER_CENTIMETER: Double = 0.393701
    }

    /**
    Parse a string out of the raw data

    - Parameters:
    - The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */

    override fun parseString(key: String): String? {
        val identifier = fieldMapper.fieldFor(key)
        return Regex.firstMatch("($identifier)(.+)\\b", data)
    }

    /**
    Parse a double out of the raw data.

    - Parameters:
    - The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseDouble(key: String): Double? {
        val identifier = fieldMapper.fieldFor(key)
        return Regex.firstMatch("($identifier)(\\w+)\\b", data)?.toDouble()
    }

    /**
    Parse a date out of the raw data

    - Parameters:
    - The human readable key we're looking for

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseDate(field: String): Date? {
        val dateString = parseString(field) ?: return null
        if (dateString.isEmpty()) return null
        return SimpleDateFormat(getDateFormat()).parse(dateString)
    }

    /**
    The string format used with an DateFormatter to parse dates. Usually 'yyyyMMdd' or 'MMddyyyy'.

    - Returns: An DateFormatter formatter string acceptable date format
     */
    override fun getDateFormat(): String {
        return "MMddyyyy"
    }

    /**
    Parse the AAMVA last name out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseFirstName(): String? {
        return parseString("firstName")
    }

    /**
    Parse the AAMVA last name out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseLastName(): String? {
        return parseString("lastName")
    }

    /**
    Parse the AAMVA middle name out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseMiddleName(): String? {
        return parseString("middleName")
    }

    /**
    Parse the AAMVA expiration date out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseExpirationDate(): Date? {
        return parseDate("expirationDate")
    }

    /**
    Parse the AAMVA issue date out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseIssueDate(): Date? {
        return parseDate("issueDate")
    }

    /**
    Parse the AAMVA date of birth out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseDateOfBirth(): Date? {
        return parseDate("dateOfBirth")
    }

    /**
    Parse the AAMVA issuing country out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseCountry(): IssuingCountry {
        return when (parseString("country")) {
            "USA" -> IssuingCountry.UnitedStates
            "CAN" -> IssuingCountry.Canada
            else -> IssuingCountry.Unknown
        }
    }

    /**
    Parse the AAMVA name truncation statuses out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseTruncationStatus(field: String): Truncation {
        return when (parseString(field)) {
            "T" -> Truncation.Truncated
            "N" -> Truncation.None
            else -> Truncation.Unknown
        }
    }

    /**
    Parse the AAMVA gender out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseGender(): Gender {
        return when (parseString("gender")) {
            "1" -> Gender.Male
            "2" -> Gender.Female
            else -> Gender.Other
        }
    }

    /**
    Parse the AAMVA eye color out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseEyeColor(): EyeColor {
        return when (parseString("eyeColor")) {
            "BLK" -> EyeColor.Black
            "BLU" -> EyeColor.Blue
            "BRO" -> EyeColor.Brown
            "GRY" -> EyeColor.Gray
            "GRN" -> EyeColor.Green
            "HAZ" -> EyeColor.Hazel
            "MAR" -> EyeColor.Maroon
            "PNK" -> EyeColor.Pink
            "DIC" -> EyeColor.Dichromatic
            else -> EyeColor.Unknown
        }
    }

    /**
    Parse the AAMVA name suffix out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseNameSuffix(): NameSuffix {
        return when (parseString("suffix")) {
            "JR" -> NameSuffix.Junior
            "SR" -> NameSuffix.Senior
            "1ST", "I" -> NameSuffix.First
            "2ND", "II" -> NameSuffix.Second
            "3RD", "III" -> NameSuffix.Third
            "4TH", "IV" -> NameSuffix.Fourth
            "5TH", "V" -> NameSuffix.Fifth
            "6TH", "VI" -> NameSuffix.Sixth
            "7TH", "VII" -> NameSuffix.Seventh
            "8TH", "VIII" -> NameSuffix.Eighth
            "9TH", "IX" -> NameSuffix.Ninth
            else -> NameSuffix.Unknown
        }
    }

    /**
    Parse the AAMVA hair color out of the raw data

    - Returns: An optional value parsed out of the raw data
     */
    override fun parseHairColor(): HairColor {
        return when (parseString("hairColor")) {
            "BAL" -> HairColor.Bald
            "BLK" -> HairColor.Black
            "BLN" -> HairColor.Blond
            "BRO" -> HairColor.Brown
            "GRY" -> HairColor.Grey
            "RED" -> HairColor.Red
            "SDY" -> HairColor.Sandy
            "WHI" -> HairColor.White
            else -> HairColor.Unknown
        }
    }

    /**
    Parse the AAMVA height out of the raw data

    - Returns: An optional value parsed out of the raw data in inches
     */
    override fun parseHeight(): Double? {
        val heightString = parseString("height") ?: return null
        val height = parseDouble("height") ?: return null

        return if (heightString.contains("cm")) {
            BigDecimal(height * INCHES_PER_CENTIMETER)
                .setScale(0, RoundingMode.HALF_UP)
                .toDouble()
        } else {
            height
        }
    }

}