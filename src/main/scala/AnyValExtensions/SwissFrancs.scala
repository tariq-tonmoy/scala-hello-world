package AnyValExtensions

class SwissFrancs(val amount: Int) extends AnyVal {
    override def toString: String = s"$amount CHF"
}
