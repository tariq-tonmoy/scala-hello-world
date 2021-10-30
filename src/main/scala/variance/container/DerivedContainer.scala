package variance.container

import variance.hierarchy._

class DerivedContainer[-A, +B, -C, +D, -E, +F, -G, +H](a: A,
                                                       //val c: C,
                                                       //private val e: E,
                                                       private[this] val g: G, // depricated
                                                       b: B,
                                                       val d: D,
                                                       private val f: F,
                                                       private[this] val h: H /* depricated */) extends BaseContainer[D, A](d, a) {

    def this(derivedContainer: DerivedContainer[A, B, C, D, E, F, G, H], a8: A, g8: G) = {
        this(a8, g8, derivedContainer.b1, derivedContainer.d, derivedContainer.f, derivedContainer.h1)
    }

    private val a1: A = a
    private var a2 = a

    private val g1 = g
    private val h1 = h

    //    Compilation Error: a3 and a4 are public
    //    val a3 = a
    //    var a4 = a

    //    Compilation Error: a5 and a6 are protected
    //    protected val a5 = a
    //    protected var a6 = a

    private var _a7 = a

    private def a7 = _a7

    def a7_=(value: A) = _a7 = value

    private val b1 = b
    private var b2 = b

    val b3 = b
    //    Compilation Error: b4 setter has a convariant in setter parameter type
    //    var b4 = b

    protected val b5 = b
    //    Compilation Error: b4 setter has a convariant in setter parameter type
    //    protected var b6 = b

    private var _b7: B = b

    def b7: B = _b7

    private def b7_=(value: B) = _b7 = value

    private def setValuesA(tempA: A) = {
        println(s"a7: $a7")
        a7 = tempA
        println(s"changed a7: $a7")
    }

    private def setValuesAB(tempA: A, tempB: B) = {
        println(s"a7: $a7")
        a7 = tempA
        println(s"changed a7: $a7")

        println(s"b7: $b7")
        b7 = tempB
        println(s"changed b7: $b7")
    }

    private def setValues(derivedContainer: DerivedContainer[A, B, C, D, E, F, G, H], a8: A, g8: G) = {
        new DerivedContainer[A, B, C, D, E, F, G, H](derivedContainer, a8, g8)
    }

}


object DerivedContainer {
    def testDerivedContainer: Unit = {
        val derivedContainer: DerivedContainer[S, T, V, S, W, T1, W1, P] = new DerivedContainer[S, T, U, V, W, U1, V1, W1](new S, new V1, new T, new V, new U1, new W1)
        derivedContainer.setValuesA(new U)
        //        derivedContainer.setValues(new U, new V)
        //        println(derivedContainer.a7)
        //        println(derivedContainer.b7)
        //        derivedContainer.b7_(new U)

        //        derivedContainer.a2 = new S
        //        derivedContainer.setValues(derivedContainer)
    }


}