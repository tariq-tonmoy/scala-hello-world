package rationals

class Rational(n: Int, d: Int) {
    require(this.checkPreconditions(n, d))

    private val gcdVal: Int = this.calculateGCD(n, d)
    val numer: Int = if (n * d < 0) then n / gcdVal else n.abs / gcdVal;
    val denom: Int = d.abs / gcdVal;

    def this(n: Int) = {
        this(n, 1)
    }


    override def toString: String = if (numer * denom < 0) s"-${numer.abs}/${denom.abs}" else s"${numer.abs}/${denom.abs}"


    def +(that: Rational): Rational = {
        this.add(that)
    }

    def +(i: Int): Rational = {
        val intRational = new Rational(i);
        this + intRational
    }

    def *(i: Int): Rational = {
        val intRational = new Rational(i)
        this * intRational
    }

    def add(that: Rational): Rational = {
        val temp_numer = (this.numer * that.denom) + (that.numer * this.denom)
        val temp_denom = this.denom * that.denom
        new Rational(
            n = temp_numer,
            d = temp_denom)
    }

    def unary_-(that: Rational): Rational = {
        new Rational(n = -that.numer, d = that.denom)
    }

    def *(that: Rational): Rational = {
        this.multiply(that)
    }

    def multiply(that: Rational): Rational = {
        new Rational(n = this.numer * that.numer, d = this.denom * that.denom)
    }

    def lessThan(that: Rational): Boolean =
        this.numer * that.denom < this.denom * that.numer

    def max(that: Rational): Rational =
        if (this.lessThan(that)) that else this

    private def checkPreconditions(_n: Int, _d: Int): Boolean = {
        if (_d != 0) true else false
    }

    private def calculateGCD(_n: Int, _d: Int): Int = {
        this.gcd(_n.abs max _d.abs, _n.abs min _d.abs)
    }

    private def gcd(max: Int, min: Int): Int = {
        if (min == 0) max else gcd(min, max % min)
    }
}
