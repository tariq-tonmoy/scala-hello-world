package implicits.helpers

import rationals.{Rational, RationalString}

object RationalNumberProvider {
    def getRarionalString_1_2 = {
        new RationalString("1/2")
    }

    def getRarionalString_3 = {
        new RationalString("3")
    }

    def getRarional3_5 = {
        new Rational(3, 5)
    }

    def getRarional_9 = {
        new Rational(9)
    }
}
