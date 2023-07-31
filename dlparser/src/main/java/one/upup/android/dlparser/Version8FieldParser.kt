package one.upup.android.dlparser


class Version8FieldMapper : FieldMapper()

class Version8FieldParser(override var data: String) :
    FieldParser(data, Version8FieldMapper())