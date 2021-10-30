import java.lang.Integer
import AnyValExtensions._

object Chapter11 {

    def testValueClasses = {
        val negativeByte: Byte = -128
        val positiveByte: Byte = 127

        val negativeShort: Short = -32768
        val positoveShort: Short = 32767

        val short: Short = negativeByte

        val intVal: Int = short

        val longVal: Long = intVal

        val floatVal: Float = longVal.toFloat

        val doubleVal: Double = floatVal
    }

    def javaIntegerComparision(x: Integer, y: Integer): Boolean = {
        x == y
    }

    def scalaIntComparison(x: Any, y: Any): Boolean = {
        x == y
    }

    def testReferenceEquality = {
        val str1: String = new String("Abc")
        val str2: String = new String("Abc")

        val refEquality = str1 eq str2
        val stringEquality = str1 == str2

        println("Reference equality = " + refEquality)
        println("Any Type equality = " + stringEquality)
    }

    def comparisonBetweenJavaAndScala = {
        val javaRes = javaIntegerComparision(142, 142)

        val scalaRes = scalaIntComparison(142, 142)

        println("This would have been false in Java: " + javaRes)
        println(scalaRes)
    }

    def throwsError: Nothing = {
        throw new Exception("From Nothing Return Type")
    }

    @main def chapterElevenTests = {
        comparisonBetweenJavaAndScala
        testReferenceEquality

        try {
            throwsError
        }
        catch {
            case ex: Exception => {
                println(ex)
            }
        }

        val dollars: Dollars = new Dollars(100)
        val swissFrancs: SwissFrancs = new SwissFrancs(100)

        val initiatedWithDollars = MoneyConverter(dollars)
        val initiatedWithCHF = MoneyConverter(swissFrancs)

        println(s"$swissFrancs is ${initiatedWithCHF.getDollars}")
        println(s"$dollars is ${initiatedWithDollars.getCHFs}")
    }
}
