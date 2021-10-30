import scala.language.postfixOps;
import rationals._

object Chapter6 {

    @main def chapterSixTests() = {
        val rational_5_10: Rational = new Rational(5, 10)
        val rational_1_2: Rational = new Rational(1, 2)
        val rational__5__4: Rational = new Rational(-5, -4)
        val rational__5_4: Rational = new Rational(-5, 4)
        val rational_6_1: Rational = new Rational(6)
        val rational_10_5: Rational = new Rational(10, 5)
        val rational_3_2: Rational = new Rational(3, 2)

        val addedRational = rational_5_10 + rational_3_2
        val multipliedRational = rational_5_10 * rational_3_2
        val operationPrecedenceRational = rational_5_10 + rational_3_2 * rational_1_2

        Console println addedRational toString;
        Console println multipliedRational toString;
        Console println operationPrecedenceRational toString;


        Console println rational__5_4 toString;
        Console println rational__5__4 toString;

        val plusMinusAdditionRational = rational_5_10 + rational__5_4
        Console println plusMinusAdditionRational toString


        val plusMinusAdditionRational2 = rational_5_10 + rational__5__4
        Console println plusMinusAdditionRational2 toString

        Console println rational__5_4 * 2 toString
    }
}
