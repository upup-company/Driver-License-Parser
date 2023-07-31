package one.upup.android.dlparser


class Version1FieldMapper : FieldMapper() {
    init {
        fields["customerId"] = "DBJ"
        fields["lastName"] = "DAB"
        fields["driverLicenseName"] = "DAA"
    }
}

class Version1FieldParser(override var data: String) : FieldParser(data, Version1FieldMapper()) {


    override fun getDateFormat(): String {
        return "yyyyMMdd"
    }

    override fun parseFirstName(): String? {
        return parseString("firstName") ?: parseDriverLicenseName("firstName")
    }

    override fun parseLastName(): String? {
        return parseString("lastName") ?: parseDriverLicenseName("lastName")
    }

    override fun parseMiddleName(): String? {
        return parseString("middleName") ?: parseDriverLicenseName("middleName")
    }

    // Parse something like 508 (5'8") into 68"
    override fun parseHeight(): Double? {
        val heightInFeetAndInches = parseString("height") ?: return null
        val height = Regex.firstMatch("([0-9]{1})", heightInFeetAndInches) ?: return null
        val inches = Regex.firstMatch("[0-9]{1}([0-9]{2})", heightInFeetAndInches) ?: return null

//        guard !height.isEmpty else { return nil }
//        guard !inches.isEmpty else { return nil }

        val calculatedHeight = height.toDouble() * 12 + inches.toDouble()

        return if (heightInFeetAndInches.contains("cm")) {
            (calculatedHeight * FieldParser.INCHES_PER_CENTIMETER)
        } else {
            calculatedHeight
        }
    }

    override fun parseNameSuffix(): NameSuffix {
        var suffix: String? = null
        if (parseString("suffix") != null) {
            suffix = parseString("suffix")
        }

        if (parseDriverLicenseName("suffix") != null) {
            suffix = parseDriverLicenseName("suffix")
        }

        return when (suffix) {
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

    private fun parseDriverLicenseName(key: String): String? {
        val driverLicenseName = parseString("driverLicenseName") ?: return null
//        TODO()
        return null

//        val namePieces = driverLicenseName.chars().split { $0 == "," }.map(String.init)
//
//        switch key {
//            case "lastName":
//            guard namePieces . indices . contains (0) else { return nil }
//            return namePieces[0]
//            case "firstName":
//            guard namePieces . indices . contains (1) else { return nil }
//            return namePieces[1]
//            case "middleName":
//            guard namePieces . indices . contains (2) else { return nil }
//            return namePieces[2]
//            case "suffix":
//            guard namePieces . indices . contains (3) else { return nil }
//            return namePieces[3]
//            default:
//            return nil
//        }
    }
}
