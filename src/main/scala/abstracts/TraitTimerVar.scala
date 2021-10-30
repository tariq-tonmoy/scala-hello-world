package abstracts

trait TraitTimerVar(val testVal: Int) {
    def hour: Int

    def hour_=(h: Int): Unit

    def minute: Int

    def minute_=(m: Int): Unit

    var second: Int

    val day: Int

    var month: Int

    val year: Int = {
        val _year = 2001
        println(s"TraitTimerVar Year: $_year -> ")
        _year
    }

    var yearVar: Int

    def yearDef: Int = {
        val _year = 2011
        println(s"TraitTimerVar YearDef: $_year -> ")
        _year
    }
}

object TraitTimerVar {
    def testTraitTimerVar = {
        new TraitTimerVar(20) {
            var _hour: Int = _
            var _minute: Int = _

            override def hour: Int = _hour

            //            Infinite Recursive Call
            //            override def hour_=(h: Int): Unit = hour = h

            override def minute: Int = _minute

            override def hour_=(h: Int): Unit = _hour = h

            override def minute_=(m: Int): Unit = _minute = m

            var second: Int = _

            override val day = 19

            var month = 10

            var yearVar: Int = 2012
        }
    }
}
