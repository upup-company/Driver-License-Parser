package one.upup.android.dlparser

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    private val data = "@ANSI 636026080102DL00410288ZA03290015DLDAQD12345678DCSPUBLICDDENDACJOHNDDFNDADQUINCYDDGNDCADDCBNONEDCDNONEDBD08242015DBB01311970DBA01312035DBC1DAU069 inDAYGRNDAG789 E OAK STDAIANYTOWNDAJCADAK902230000DCF83D9BN217QO983B1DCGUSADAW180DAZBRODCK12345678900000000000DDB02142014DDK1ZAZAANZABZAC"


    private val parsedLicense by lazy {
        LicenseParser(data).parse()
    }

    @Test
    fun assertNameCorrect() {
        println(parsedLicense)
        assertEquals(parsedLicense.firstName, "JOHN")
    }
}