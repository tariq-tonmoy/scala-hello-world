package abstracts

class ConcreteTimer extends AbstractTimeVar(100) with TraitTimerVar(200) {

    override val testVal: Int = 100 * 200
    var _hour: Int = _
    var _minute: Int = _
    var _month: Int = 12

    var hour: Int = _
    //    Compilation Error: Either use var hour from abstract class or hour's getter setter pair from trait
    //    override def hour: Int = _hour
    //    def hour_=(h: Int): Unit = _hour = h

    override def minute: Int = _minute
    //    Compilation Error: Cannot override a mutable variable
    //    override def hour_=(h: Int): Unit = _hour = h
    //    override def minute_=(m: Int): Unit = _minute = m

    def minute_=(m: Int): Unit = _minute = m

    var second: Int = testVal

    // when val and def in two different superclasses, go with VAL
    override val day: Int = 28


    // Way to inherit from two classes: 1. val month 2. var month
    // result: val month + def month_=
    // code inside val is executed only once. otherwise, the initial value persists. code inside never executes more than once
    // what happens when accessing concurrently?
    override val month: Int = {
        println("START: override val month: Int ")
        println(month)
        println(_month)
        println("END: override val month: Int ")

        //        Compilation error: Super on month cannot be called since month in super is abstract
        //        println(s"calling super-> ${super.month}")

        _month
    }

    //    Infinite recursion:
    //    def month_=(mon: Int): Unit = month = mon

    def month_=(mon: Int): Unit = {
        println("month_=")
        _month = mon
        println(_month)
    }

    override val year: Int = {
        val _year = 2000
        println(s"ConcreteTimer Year: $_year -> ")
        //        Compilation error: Cannot call super on val from scala 2.7.0
        //        println(s"ConcreteTimer Year: $_year -> ${super.year}")
        _year
    }

    //    Compilation Error: When one abstract var and one implemented var of same name, cannot override, cannot change in derived class
    //    var yearVar: Int = _

    override def yearDef: Int = super.yearDef
}