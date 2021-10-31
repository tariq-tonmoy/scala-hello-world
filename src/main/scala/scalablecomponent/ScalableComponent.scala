package scalablecomponent

import scalablecomponent.abstracttypes.{AbsCell, DoubleCell, IntCell, StringCell}
import scalablecomponent.singletontypes.{Base, Derived}

object ScalableComponent {

    def testAbsCellTypes(absCell: AbsCell) = {
        absCell match {
            case _: AbsCell {type T = Int} => "Int Type"
            case _: AbsCell {type T = Double} => "Double Type"
            case _: AbsCell {type T = String} => "String Type"
            case _ => "Undefined Type"
        }
    }

    def testImmutableTypeTest(flip: Boolean) = {
        if (flip)
            new AbsCell {
                override type T = Int
                override val init: Int = {
                    set(-1);
                    -1
                }
            }
        else
            new AbsCell {
                override type T = String
                override val init: String = {
                    set("Hello");
                    "Hello"
                }
            }
    }

    def testAbstractTypes = {
        val cell = new AbsCell {
            override type T = Int
            override val init: Int = {
                // Since var cannot be LAZY!
                set(1);
                1
            }
        }
        val stringCell = new StringCell
        val intCell = new IntCell
        val doubleCell = new DoubleCell

        val assignedIntCell: AbsCell {type T = Int} = cell
        val assignedIntCell2: AbsCell {type T = Int} = intCell

        //        Compilation Error
        //        val intCell2: IntCell = cell

        //        Compilation Error
        //        val assignedDoubleCell: AbsCell {type T = Double} = intCell

        // Does Not Work!!!!
        println(testAbsCellTypes(cell))
        println(testAbsCellTypes(intCell))
        println(testAbsCellTypes(doubleCell))
        println(testAbsCellTypes(stringCell))
        // END: Does Not Work!!!!


        val testFalse = testImmutableTypeTest(false)
        println(testFalse.get)

        val testTrue = testImmutableTypeTest(true)
        println(testTrue.get)

        var test: AbsCell#T = testImmutableTypeTest(false).get
        println(test)

        test = testImmutableTypeTest(true).get
        println(test)

        // Compilation Error: Does not work since "#INT". Only works with "#T"
        // val cell2: AbsCell#Int = intCell.get
    }

    def testSingletonTypes = {
        val derived = new Derived
        val resD = derived.decr.incr.decr

        val base = new Base
        val resB = base.incr

        val baseFromDerived: Base = derived.decr

        val derivedFromBase: Derived = derived.incr
        println(derived.incr)
        println(baseFromDerived)

        //        Does not work
        //        val derivedFromBase2: Derived = base.incr
    }

    @main def scalableComponentTests = {
        testAbstractTypes
        testSingletonTypes
    }
}
