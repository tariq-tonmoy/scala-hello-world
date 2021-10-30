import AnyValExtensions.*

import scala.annotation.targetName

class MoneyConverter(dollarAmount: Dollars) {
    require(dollarAmount.amount > 0)

    def getDollars: Dollars = dollarAmount

    def getCHFs: SwissFrancs = {
        new SwissFrancs((this.dollarAmount.amount * MoneyConverter.dollarToCHFConversionRate).asInstanceOf[Int])
    }

}

object MoneyConverter {
    private val dollarToCHFConversionRate: Double = 1.42

    private def getDollars(swissFrancs: SwissFrancs): Dollars = {
        new Dollars((swissFrancs.amount * (1 / MoneyConverter.dollarToCHFConversionRate)).asInstanceOf[Int])
    }

    def apply(dollars: Dollars): MoneyConverter = {
        new MoneyConverter(dollars)
    }

    @targetName("initiateWithSwissFrancs")
    def apply(swissFrancs: SwissFrancs): MoneyConverter = {
        val dollars = getDollars(swissFrancs)
        new MoneyConverter(dollars)
    }
}


