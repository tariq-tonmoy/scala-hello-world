package abstracts

// Compilation Error: traits cannot have byname parameters
// trait Initialization(val testVal: Int, testParam: => Int)

abstract class Initialization(val testVal: Int, testParam: => Int, testHoFunc: => Int) {
    val valBeforeReuqire: Int = testVal + 10
    require(testRequirements)

    val abstractVal: Int
    val abstractHOFunc: () => Int

    val valAfterRequre: Int = testVal + 20

    private def testRequirements: Boolean = {
        println("\n\nStarting to print inside testRequirements")
        println(s"Inside Initialization -> require -> testRequirements: \n" +
          s"testHoFunc = ${testHoFunc}; \n" + // Called First
          s"testVal = $testVal; \n" +
          s"testParam = $testParam; \n" + // Called Second
          s"abstractVal = $abstractVal;\n" +
          s"valBeforeRequre = $valBeforeReuqire; \n" +
          s"abstractHOFunc = ${if (abstractHOFunc == null) "!!!abstractHOFunc is Null!!!" else abstractHOFunc()}; \n" + // Runtime Error: Since abstractHOFunc is abstract, it is not initialized
          s"testHoFunc = ${testHoFunc}; \n" + // Called Third
          s"valAfterRequre = $valAfterRequre; \n" +
          s"testParam + testVal = ${testParam + testVal}\n\n") // Called Fourth

        true
    }

    private def printAbstractVal = {
        println(s"Inside Initialization -> printAbstractVal: abstractVal = $abstractVal; abstractHOFunc = ${abstractHOFunc()}\n\n")
    }
}


object Initialization {

    private def getTestParam: Int = {
        val _testParam = 89
        println(s"Inside ObjectInitialization -> getTestParam: _testParam = $_testParam")
        _testParam
    }

    private def getTestVal: Int = {
        val _testVal = 29
        println(s"Inside ObjectInitialization -> getTestVal: _testVal = $_testVal")
        _testVal
    }

    private def hoFunc(): Int = {
        val _hoVal = 63
        println(s"Inside ObjectInitialization -> hoFunc: _hoVal = $_hoVal")
        _hoVal
    }

    private def getAbstractVal: Int = {
        val _abstractVal: Int = 11
        println(s"Inside ObjectInitialization -> getAbstractVal: _abstractVal = $_abstractVal")
        _abstractVal
    }

    def testInitialization = {
        println("\nInside ObjectInitialization")

        println("Regular abstract value initialization")
        val init = new Initialization(getTestVal, getTestParam, hoFunc()) {
            override val abstractVal: Int = getAbstractVal
            override val abstractHOFunc: () => Int = hoFunc
        }
        init.printAbstractVal

        // Compilation Error: Early Initialization dropped in Scala 3
        //        println("Pre-initializing abstract values")
        //        val preInit = new {
        //            val abstractVal: Int = getAbstractVal
        //            val abstractHOFunc: () => Int = hoFunc
        //        } with Initialization(getTestVal, getTestParam, hoFunc())

    }
}