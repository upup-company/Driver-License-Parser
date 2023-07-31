package one.upup.android.dlparser

class Version3FieldMapper : FieldMapper() {
    init {
        fields["firstName"] = "DCT"
    }
}

class Version3FieldParser(override var data: String) :
    FieldParser(data, Version3FieldMapper()) {

    override fun getDateFormat(): String {
        return "yyyyMMdd"
    }
}