package one.upup.android.dlparser

class Version4FieldMapper : FieldMapper()

class Version4FieldParser(override var data: String) : FieldParser(data, Version4FieldMapper())