package one.upup.android.dlparser


// Defines Field Mapping Behavior
public interface FieldMapping {
    // A list of AAMVA field designator mappings (e.g. "firstName" => "DAC")
    val fields: MutableMap<String, String>

    /**
    Determine the AAMVA field designator for a particular human readable key.

    - Parameters:
    - key: The human readable key

    - Returns: The AAMVA field designator
     */
    fun fieldFor(key: String): String
}

// A basic Field Mapping implementation based on the AAMVA Version 8 standard
open class FieldMapper : FieldMapping {

    // A list of AAMVA field designator mappings (e.g. "firstName" => "DAC")
    override val fields: MutableMap<String, String> = mutableMapOf(
        "firstName" to "DAC",
        "lastName" to "DCS",
        "middleName" to "DAD",
        "expirationDate" to "DBA",
        "issueDate" to "DBD",
        "dateOfBirth" to "DBB",
        "gender" to "DBC",
        "eyeColor" to "DAY",
        "height" to "DAU",
        "streetAddress" to "DAG",
        "city" to "DAI",
        "state" to "DAJ",
        "postalCode" to "DAK",
        "customerId" to "DAQ",
        "documentId" to "DCF",
        "country" to "DCG",
        "middleNameTruncation" to "DDG",
        "firstNameTruncation" to "DDF",
        "lastNameTruncation" to "DDE",
        "streetAddressSupplement" to "DAH",
        "hairColor" to "DAZ",
        "placeOfBirth" to "DCI",
        "auditInformation" to "DCJ",
        "inventoryControlNumber" to "DCK",
        "lastNameAlias" to "DBN",
        "firstNameAlias" to "DBG",
        "suffixAlias" to "DBS",
        "suffix" to "DCU"
    )

    /**
    Determine the AAMVA field designator for a particular human readable key.

    - Parameters:
    - key: The human readable key

    - Returns: The AAMVA field designator
     */
    public override fun fieldFor(key: String): String {
        return fields[key] ?: ""
    }
}