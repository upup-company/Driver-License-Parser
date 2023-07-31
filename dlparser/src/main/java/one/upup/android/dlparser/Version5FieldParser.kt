package one.upup.android.dlparser

class Version5FieldMapper : FieldMapper()

class Version5FieldParser(override var data: String) : FieldParser(data, Version5FieldMapper())