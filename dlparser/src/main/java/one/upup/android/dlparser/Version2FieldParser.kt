package one.upup.android.dlparser


class Version2FieldMapper : FieldMapper() {
    init {
        fields["firstName"] = "DCT"
    }
}

class Version2FieldParser(override var data: String) : FieldParser(data, Version2FieldMapper())