import implicits.ImplicitTypeConversion

object Chapter21 {

    implicit def convertIntToDouble(x: Int): Double = x.toDouble

    implicit def convertDoubleToInt(x: Double): Int = x.toInt

    def testConversionToExpectedType = {
        val i: Int = 9.553d
        val d: Double = 3

        println(s"Int: $i")
        println(s"Double: $d")

        //ArrayAssoc -> implicit conversion in predef
        val map: Map[Int, Int] = Map(1 -> 2, Tuple2(2, 3))
    }

    @main def chapterTwentyOneTests = {
        ImplicitTypeConversion.testRationals
        ImplicitTypeConversion.testAnimalSounds
        testConversionToExpectedType
        ImplicitTypeConversion.testAnimalFoods
        ImplicitTypeConversion.testStudents
    }

}
