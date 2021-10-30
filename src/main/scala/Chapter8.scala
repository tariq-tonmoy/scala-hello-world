object Chapter8 {

    def testClosureInScope = {
        val more = 50
        val incrementOp: Int => Int = x => x + more
        val resOuterScore = incrementOp(30)

        def testClosureInnerScore: Int = {
            val more = 90
            incrementOp(20)
        }

        Console println s"Closure Outer Score: $resOuterScore"
        Console println s"Closure Inner Score: ${testClosureInnerScore}"
    }

    var another = 10

    def add(x: Int): Int = {
        x + another
    }

    def testClosureInLobalScope = {
        another = 9
        val add_another = add _
        var res = add_another(30)
        another = 8

        println(res)
        println(add_another(30))
    }

    def testClosureInSingleScope = {
        var more = 20

        val incrementOp: Int => Int = x => x + more

        val add40_20 = incrementOp(40)
        more = 30

        val add90_30 = incrementOp(90)

        Console println s"40+20 = $add40_20"
        Console println s"90+30 = $add90_30"

        more = 195
        val add1: Int => Int = incrementOp(_)

        more = 160
        val add2 = incrementOp(_)

        more = 75
        Console println s"add1 -> 4 = ${add1(4)}"
        Console println s"add2 -> 9 = ${add2(9)}"
    }

    def testScope = {
        val varName: String = "Hello World"
        val varNameOuter: String = "Outer Scope"

        def testInnerScope = {
            val varNameInner: Int = 10
            val varName: Int = 192

            def testInnerMostScope = {
                val varNameInnerMost: Double = 3.14
                val varName: Double = 2.17
                Console println s"InnerMost varNameOuter: $varNameOuter"
                Console println s"InnerMost varName: $varName"
                Console println s"InnerMost varNameInner: $varNameInner"
                Console println s"InnerMost varNameInnerMost: $varNameInnerMost"
            }

            testInnerMostScope
            Console println ""
            Console println s"Inner varNameOuter: $varNameOuter"
            Console println s"Inner varName: $varName"
            Console println s"Inner varNameInner: $varNameInner"
        }

        testInnerScope
        Console println ""

        Console println s"ScopeStart varNameOuter: $varNameOuter"
        Console println s"ScopeStart varName: $varName"

    }

    val addOp: (Int, Int) => Int = (x: Int, y: Int) => {
        Console println s"Adding $x and $y"
        x + y
    }

    val productOp: (Int, Int) => Int = (x: Int, y: Int) => x * y

    def testFirstClassFunctions(x: Int, y: Int, operation: (Int, Int) => Int): Unit = {
        val result = operation(x, y)
        println(result)
    }

    def testFirstClassFunctionOnScalaLibrary(operation: (Int, Int) => Int, seed: Int, numbers: Int*) = {
        numbers.foreach(x => {
            val singleres = operation(x, seed)
            Console print s"$singleres "
        })
        println
    }

    def testPartiallyAppliedFirstClassFunction(operation: (Int, Int) => Int, seed: Int, numbers: Int*) = {
        val partialOperation = operation(_: Int, seed)
        numbers.foreach(x => {
            val singleres = partialOperation(x);
            Console print s"$singleres "
        })
        println
        numbers.foreach(partialOperation)
    }

    @main def chapterEightTests(): Unit = {
        testScope
        testFirstClassFunctions(2, 4, addOp)
        testFirstClassFunctions(9, 16, addOp)
        testFirstClassFunctions(8, 9, productOp)

        testFirstClassFunctionOnScalaLibrary(addOp, 10, 2, 4, 6, 7, 10)
        testFirstClassFunctionOnScalaLibrary(productOp, 9, 2, 4, 6, 7, 10)

        testPartiallyAppliedFirstClassFunction(addOp, 43, 0, 4, 8, 12)

        testClosureInScope
        testClosureInSingleScope
        testClosureInLobalScope
    }
}
