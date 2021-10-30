object Chapter5 {

    def stringInterpolationTests(): Unit = {
        val testStr =
            """"Hello", "World"!
              |"Hello","Scala"!
              |"Hello" \";"'""".stripMargin
        println(testStr)

        println()
        val helloVar: String = "Hello"

        val testS: String = s"$helloVar, World!"

        println(testS)

        println()
        val testSWithTripleQuote =
            s""""$helloVar", "Wrold!"
               |"$helloVar", "Scala!"
               |"$helloVar", "testSWithTrippleQuote!"
               |""\" $${} "$${3 * 7}" = "${3 * 7}"""".stripMargin
        println(testSWithTripleQuote)

        println()
    }

    def testOperators(): Unit = {
        val target: OperatorTestTarget = OperatorTestTarget(523)
        val target2: OperatorTestTarget = OperatorTestTarget(325)
        val list: TraversibleList = TraversibleList(1, 2, 3, 4, 5, 6, 7, 8)

        val addedRes = target infixAddition 7
        println(addedRes)

        val listedAddition = target infixAddition(1, 2, 3, 4)
        println(listedAddition)

        val negateFunc: OperatorTestTarget => Double = -target
        val negate: Double = -target
        val negateList = -list

        println(negate)
        println(negateFunc(target2))
        Console println negateList.mkString(", ")
        println()
    }

    def testOperatorPrecedence(): Unit = {
        val target: OperatorTestTarget = OperatorTestTarget(523)
        val target_20: OperatorTestTarget = OperatorTestTarget(20)
        val target_10: OperatorTestTarget = OperatorTestTarget(10)

        val targetCompilation = target + target_20 * target_10 :: target
        val res: Double = OperatorTestTarget getValue targetCompilation

        println(res)

        val ressAdd: Double = OperatorTestTarget getValue (target add target_20 add target_10 add targetCompilation)
        println(ressAdd)
    }

    @main def chapterFiveTests = {
        //Cannot call it without '()'
        //Without '()' acts as operator
        stringInterpolationTests()
        testOperators()
        testOperatorPrecedence()
    }

}
