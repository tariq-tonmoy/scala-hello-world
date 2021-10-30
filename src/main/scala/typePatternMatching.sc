abstract class Currency(amount: Int) {
    def convertCurrency(): Double;
}

case class USD(amount: Int) extends Currency(amount) {
    override def convertCurrency(): Double = {
        amount
    }
}

case class NZD(amount: Int) extends Currency(amount) {
    override def convertCurrency(): Double = {
        amount / 1.50
    }
}

case class CAD(amount: Int) extends Currency(amount) {
    override def convertCurrency(): Double = {
        amount / 1.3
    }
}

def convertCurrency(currencyService: Currency): Unit = {
    currencyService match {
        case usd: USD => {
            println("USD: $" + currencyService.convertCurrency())
        }
        case nzd: NZD => {
            println("NZD -> USD: $" + currencyService.convertCurrency())
        }
        case cad: CAD => {
            println("CAD -> USD: $" + currencyService.convertCurrency())
        }
        case _ => {
            println("Condition not met")
        }
    }
}

val usdCurrency: Currency = USD(100);
convertCurrency(usdCurrency);

val nzdCurrency: Currency = NZD(100);
convertCurrency(nzdCurrency);

val cadCurrency: Currency = CAD(100);
convertCurrency(cadCurrency);