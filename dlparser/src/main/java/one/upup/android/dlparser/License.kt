package one.upup.android.dlparser

import java.util.Date


// A Representation of the scanned AAMVA License
public data class License(
    override val firstName: String? = null,
    override val lastName: String? = null,
    override val middleName: String? = null,
    override val expirationDate: Date? = null,
    override val issueDate: Date? = null,
    override val dateOfBirth: Date? = null,
    override val gender: Gender = Gender.Unknown,
    override val eyeColor: EyeColor = EyeColor.Unknown,
    override val height: Double? = null,
    override val streetAddress: String? = null,
    override val city: String? = null,
    override val state: String? = null,
    override val postalCode: String? = null,
    override val customerId: String? = null,
    override val documentId: String? = null,
    override val country: IssuingCountry = IssuingCountry.Unknown,
    override val middleNameTruncation: Truncation = Truncation.Unknown,
    override val firstNameTruncation: Truncation = Truncation.Unknown,
    override val lastNameTruncation: Truncation = Truncation.Unknown,
    override val streetAddressSupplement: String? = null,
    override val hairColor: HairColor = HairColor.Unknown,
    override val placeOfBirth: String? = null,
    override val auditInformation: String? = null,
    override val inventoryControlNumber: String? = null,
    override val lastNameAlias: String? = null,
    override val firstNameAlias: String? = null,
    override val suffixAlias: String? = null,
    override val suffix: NameSuffix = NameSuffix.Unknown,
    override val version: String? = null,
    override val pdf417: String? = null,
) : ParsedLicense {

    /**
    Determines if the license is expired based on the `expirationDate`

    Returns: True when the current date is past the expiration date, false otherwise.
     */
    override fun isExpired(): Boolean {
        return expirationDate?.before(Date()) == true
    }

    /**
    Determines if the license has been issued based on the `issueDate`

    Returns: True when the current date is past the issue date, false otherwise.
     */
    override fun hasBeenIssued(): Boolean {
        return issueDate?.after(Date()) == true
    }

    /**
    Determines if enough of the license data is present to be useful for most things.

    Returns: True when a set of essential properties are non-empty, false otherwise.
     */

    override fun isAcceptable(): Boolean {
        return true
//        guard !isExpired() else { return false }
//        guard hasBeenIssued () else { return false }
//        guard expirationDate != nil else { return false }
//        guard lastName != nil else { return false }
//        guard firstName != nil else { return false }
//        guard middleName != nil else { return false }
//        guard issueDate != nil else { return false }
//        guard dateOfBirth != nil else { return false }
//        guard height != nil else { return false }
//        guard streetAddress != nil else { return false }
//        guard city != nil else { return false }
//        guard state != nil else { return false }
//        guard postalCode != nil else { return false }
//        guard documentId != nil else { return false }
//        return true
    }
}

// Represents the behavior of a parsed license
public interface ParsedLicense {
    // The license holder's first/given name
    val firstName: String?

    // The license holder's last/given name
    val lastName: String?

    // The license holder's middle name
    val middleName: String?

    // The expiration date of the license
    val expirationDate: Date?

    // The issue date of the license
    val issueDate: Date?

    // The license holder's date of birth
    val dateOfBirth: Date?

    // The license holder's gender
    val gender: Gender

    // The license holder's eye color
    val eyeColor: EyeColor

    // The license holder's height
    val height: Double?

    // The license holder's street address
    val streetAddress: String?

    // The license holder's city
    val city: String?

    // The license holder's state
    val state: String?

    // The license holder's postal code
    val postalCode: String?

    // The license holder's customer Id (e.g. Driver License Number)
    val customerId: String?

    // A unique document identifier
    val documentId: String?

    // The license's issuing country
    val country: IssuingCountry

    // A determination of if the middle name was truncated
    val middleNameTruncation: Truncation

    // A determination of if the first name was truncated
    val firstNameTruncation: Truncation

    // A determination of if the last name was truncated
    val lastNameTruncation: Truncation

    // The license holder's supplemental street address
    val streetAddressSupplement: String?

    // The license holder's hair color
    val hairColor: HairColor

    // The license holder's place of birth
    val placeOfBirth: String?

    // The license issuer's audit information
    val auditInformation: String?

    // The license issuer's
    val inventoryControlNumber: String?

    // The license holder's last name alias
    val lastNameAlias: String?

    // The license holder's first name alias
    val firstNameAlias: String?

    // The license holder's name suffix alias
    val suffixAlias: String?

    // The license holder's name suffix
    val suffix: NameSuffix

    // The AAMVA version to which this parsed license conforms
    val version: String?

    // The raw pdf417 scan data used to build this parsed license
    val pdf417: String?

    /**
    Determine if the license has expired

    - Returns: True or False
     */
    fun isExpired(): Boolean

    /**
    Determine if the license has been issues

    - Returns: True or false
     */
    fun hasBeenIssued(): Boolean

    /**
    Determine if the license has enough valid data

    - Returns: True or False
     */
    fun isAcceptable(): Boolean
}