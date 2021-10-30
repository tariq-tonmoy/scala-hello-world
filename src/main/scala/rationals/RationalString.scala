package rationals


class RationalString(private val rational: String) {

    require(checkRational)

    private def checkRational: Boolean = {
        rational.trim.toCharArray.forall(x => x == '/' || (x >= '0' && x <= '9')) && rational.filter(x => x == '/').length <= 1
    }

    def getRationalParts: (Int, Int) = {
        val numbers = rational.trim.split('/').map(x => x.trim.toInt)
        (numbers(0), if (numbers.length == 1) 1 else numbers(1))
    }
}

object RationalString {
    implicit def convertToRational(rationalString: RationalString): Rational = {
        val numbers = rationalString.getRationalParts
        new Rational(numbers._1, numbers._2)
    }
}
