package one.upup.android.dlparser


// A Parser for creating ParsedLicense objects
public class LicenseParser(
    // The AAMVA PDF417 raw barcode data being used for parsing
    private val dataInput: String,
) {

    private val version: String? by lazy {
        parseVersion()
    }

    // The FieldParsing object to aide in parsing individual fields
    private val fieldParser: FieldParsing by lazy {
        versionBasedFieldParsing()
    }

    private val elementIds = listOf(
        "DCS",
        "DDE",
        "DAC",
        "DDF",
        "DAD",
        "DDG",
        "DCA",
        "DCB",
        "DCD",
        "DBD",
        "DBB",
        "DBA",
        "DBC",
        "DAU",
        "DAY",
        "DAG",
        "DAI",
        "DAJ",
        "DAK",
        "DCF",
        "DCG",
        "DAW",
        "DAZ",
        "DCK",
        "DDB",
        "DDK",
        "ZAZ",
        "ZAB",
        "ZAC",
        "DBJ",
        "DAB",
        "DAA",
        "DCT",
    )


    private val data: String by lazy {
        var output = dataInput
        elementIds.forEach {
            output = output.replace(it, "\n$it")
        }
        output
    }

    /**
    Parses the AAMVA PDF417 raw barcode data based on the specific AAMVA document version

    - Returns: A ParsedLicense with all available parsed fields
     */
    public fun parse(): ParsedLicense {


        return License(
            firstName = fieldParser.parseFirstName(),
            lastName = fieldParser.parseLastName(),
            middleName = fieldParser.parseMiddleName(),
            expirationDate = fieldParser.parseExpirationDate(),
            issueDate = fieldParser.parseIssueDate(),
            dateOfBirth = fieldParser.parseDateOfBirth(),
            gender = fieldParser.parseGender(),
            eyeColor = fieldParser.parseEyeColor(),
            height = fieldParser.parseHeight(),
            streetAddress = fieldParser.parseString("streetAddress"),
            city = fieldParser.parseString("city"),
            state = fieldParser.parseString("state"),
            postalCode = fieldParser.parseString("postalCode"),
            customerId = fieldParser.parseString("customerId"),
            documentId = fieldParser.parseString("documentId"),
            country = fieldParser.parseCountry(),
            middleNameTruncation = fieldParser.parseTruncationStatus("middleNameTruncation"),
            firstNameTruncation = fieldParser.parseTruncationStatus("firstNameTruncation"),
            lastNameTruncation = fieldParser.parseTruncationStatus("lastNameTruncation"),
            streetAddressSupplement = fieldParser.parseString("streetAddressSupplement"),
            hairColor = fieldParser.parseHairColor(),
            placeOfBirth = fieldParser.parseString("placeOfBirth"),
            auditInformation = fieldParser.parseString("auditInformation"),
            inventoryControlNumber = fieldParser.parseString("inventoryControlNumber"),
            lastNameAlias = fieldParser.parseString("lastNameAlias"),
            firstNameAlias = fieldParser.parseString("firstNameAlias"),
            suffixAlias = fieldParser.parseString("suffixAlias"),
            suffix = fieldParser.parseNameSuffix(),
            version = version,
            pdf417 = dataInput,
        )
    }

    private fun versionBasedFieldParsing(): FieldParsing {
        return when (version) {
            "01" -> Version1FieldParser(data)
            "02" -> Version2FieldParser(data)
            "03" -> Version3FieldParser(data)
            "04" -> Version4FieldParser(data)
            "05" -> Version5FieldParser(data)
            "08" -> Version8FieldParser(data)
            else -> FieldParser(data)
        }
    }

    private fun parseVersion(): String? {
        return Regex.version(dataInput)
    }

}