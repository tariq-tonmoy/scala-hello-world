package abstracts

// Cannot have abstract members without abstract keywork before class
abstract class AbstractTimeVar(val testVal: Int) {
    var hour: Int
    var minute: Int
    var second: Int

    def day: Int

    val month: Int

    val year: Int = {
        val _year = 1990
        println(s"AbstractTimeVar Year: $_year -> ")
        _year
    }

    var yearVar: Int = {
        val _year = 1991
        println(s"AbstractTimeVar Year: $_year -> ")
        _year
    }

    def yearDef: Int = {
        val _year = 2010
        println(s"AbstractTimeVar YearDef: $_year -> ")
        _year
    }
}

object AbstractTimeVar {

    def testAbstractTimeVar = {
        new AbstractTimeVar(10) {
            var hour: Int = 24
            var minute: Int = 35
            var second: Int = 42

            def day: Int = 21

            val month: Int = 9
        }
    }
}