package AnyValExtensions

class Dollars(val amount: Int) extends AnyVal {

    override def toString: String = s"$$$amount"
}
