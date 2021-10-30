import expressions._

object Chapter15 {

    def show(optionStr: Option[String]): String = {
        optionStr match {
            case Some(s) => s
            case None => "??"
        }
    }

    // Needs to be Exhaustive
    val getSecondElement: List[Int] => Int = {
        case x :: y :: _ => y
        case _ => -1
    }

    val getSecondElementByPartialFunction: PartialFunction[List[Int], Int] = {
        case x :: y :: _ => y
    }

    def testParticalFunction = {
        val list: List[Int] = List(1, 2, 3)
        val secondElement = this.getSecondElement(list)

        println(secondElement)

        val emptyElement = this.getSecondElement(List())
        println(emptyElement)

        val secondElementByPartialFunction = this.getSecondElementByPartialFunction(List(1, 2, 3))
        println(secondElementByPartialFunction)

        val secondElementByPartialFunctionWithTwoElements = this.getSecondElementByPartialFunction(List(1, 2))
        println(secondElementByPartialFunctionWithTwoElements)

        // Runtime Error
        // val secondElementByPartialFunctionWithOneElements = this.getSecondElementByPartialFunction(List(1))
        // println(secondElementByPartialFunctionWithOneElements)

        // Runtime Error
        // val secondElementByPartialFunctionWithNoElements = this.getSecondElementByPartialFunction(List())
        // println(secondElementByPartialFunctionWithNoElements)

        val isDefinedWithNoElement = this.getSecondElementByPartialFunction.isDefinedAt(List())
        val isDefinedWithOneElement = this.getSecondElementByPartialFunction.isDefinedAt(List(1))
        val isDefinedWithTwoElements = this.getSecondElementByPartialFunction.isDefinedAt(List(1, 2))
        val isDefinedWithThreeElements = this.getSecondElementByPartialFunction.isDefinedAt(List(1, 2, 3))
        val isDefinedWithFourElements = this.getSecondElementByPartialFunction.isDefinedAt(List(1, 2, 3, 4))

        println(s"isDefinedWithNoElement: $isDefinedWithNoElement")
        println(s"isDefinedWithOneElement: $isDefinedWithOneElement")
        println(s"isDefinedWithTwoElements: $isDefinedWithTwoElements")
        println(s"isDefinedWithThreeElements: $isDefinedWithThreeElements")
        println(s"isDefinedWithFourElements: $isDefinedWithFourElements")
    }

    def testPatternInVariableDefinitions = {
        val BinOp(op, left, right): Expr = BinOp("*", UnOp("-", Number(10)), Var("x"))

        println(op)
        println(left)
        println(right)

        // Compilation Error: expressions.NonCaseBinOp cannot be used as an extractor in a pattern because it lacks an unapply or unapplySeq method
        // val NonCaseBinOp(nonCaseOp, nonCaseleft, nonCaseRight) = new NonCaseBinOp("*", new NonCaseUnOp("-", new NonCaseNumber(10)), new NonCaseVar("x"))
    }

    def testOptions = {
        val cityMap: Map[String, String] = Map("Bangladesh" -> "Dhaka", "Japan" -> "Tokyo", "Thailand" -> "Bangkok")

        println("Bangladesh")
        println(cityMap("Bangladesh"))
        println(cityMap.get("Bangladesh"))
        println(show(cityMap.get("Bangladesh")))
        println()

        println("North Korea")
        // Runtime Error
        // println(cityMap("North Korea"))
        println(cityMap.get("North Korea"))
        println(show(cityMap.get("North Korea")))
        println()
    }

    def testNonCaseClassFacilities = {
        println("NON CASE CLASS")

        val leftArg: NonCaseExpr = new NonCaseUnOp("-", new NonCaseNumber(30))
        val rightArg: NonCaseExpr = new NonCaseBinOp("x", new NonCaseVar("p"), new NonCaseNumber(90))
        val operator: String = "+"

        val binOpParams: (String, NonCaseExpr, NonCaseExpr) => NonCaseBinOp = new NonCaseBinOp(_, _, _)

        val nonCaseExpr: NonCaseExpr = binOpParams(operator, leftArg, rightArg)
        val nonCaseBinOp: NonCaseBinOp = binOpParams(operator, leftArg, rightArg)

        println(nonCaseBinOp.toString)

        // Compilation Error
        // println(binOp.operator)

        println(nonCaseExpr.hashCode())
        println(nonCaseBinOp.hashCode())
    }

    def testCaseClassFacilities = {
        println("Case Class Facilities")

        val binOpParams = () => BinOp("+", UnOp("-", Number(30)), BinOp("x", Var("p"), Number(90)))

        val arg1 = UnOp("-", Number(30))
        val arg2 = BinOp("x", Var("p"), Number(90))
        val operator = "+"

        val parameterizedBinOp = BinOp(_, _, _)

        val expr: Expr = binOpParams()

        val binOp: BinOp = binOpParams()
        println(expr.toString)

        println(binOp.operator)


        println("Same Hash Codes:")
        println(expr.hashCode())
        println(binOp.hashCode())
        println(parameterizedBinOp(operator, arg1, arg2).hashCode())
        if (expr.hashCode() == binOp.hashCode() && parameterizedBinOp(operator, arg1, arg2).hashCode() == binOp.hashCode()) {
            println("Hash Codes are same")
        }

        println("Different Hash Codes:")
        println(parameterizedBinOp(operator, arg2, arg1).hashCode())

        println("Test Copy Case Class")
        val copyTest1: TestCaseClass = TestCaseClass(10, 20, 30, 40, 50, 60)
        println(copyTest1)
        val copyTest2: TestCaseClass = copyTest1.copy(num1 = 9)
        println(copyTest2)
    }

    def pattentMatchingTests = {
        val patternMatcher: PatternMatcher = new PatternMatcher
        println(patternMatcher.constantPatternMatching(true))
        println(patternMatcher.constantPatternMatching(Math.PI))

        println("test variablePatternMatching")
        println(patternMatcher.variablePatternMatching(Math.PI))
        println(patternMatcher.variablePatternMatching(Math.E))
        println()

        println("test variableAsConstantPatternMatching")
        println(patternMatcher.variableAsConstantPatternMatching(Math.E, patternMatcher))
        println(patternMatcher.variableAsConstantPatternMatching(Math.PI, patternMatcher))
        println(patternMatcher.variableAsConstantPatternMatching(Math.PI + Math.E, patternMatcher))
        println(patternMatcher.variableAsConstantPatternMatching(1.414292654, patternMatcher))
        println(patternMatcher.variableAsConstantPatternMatching(1, patternMatcher))
        println()

        println("test sequencePatternMatching")
        patternMatcher.sequencePatternMatching(List(1, 2, 3, 4))
        patternMatcher.sequencePatternMatching(List(0, 1, 2, 3, 4))
        patternMatcher.sequencePatternMatching(0, 1, 2)
        patternMatcher.sequencePatternMatching(0, 2)
        println()

        println("test typedPatternMatching")
        println(patternMatcher.typedPatternMatching("Hello, World!"))
        println(patternMatcher.typedPatternMatching(Map(1 -> "Hello, ", 2 -> "World!", "WOW" -> 3)))
        println(patternMatcher.typedPatternMatching(List("psa", "q", "r", 1)))
        println(patternMatcher.typedPatternMatching(Array(1, 1.001d, 9.0002d, 9)))
        println(patternMatcher.typedPatternMatching(Array(1, 1, 1, 1, 90)))
        println(patternMatcher.typedPatternMatching(Array("1", 1, 1, "1", 90)))
        println(patternMatcher.typedPatternMatching(Array(1, 1, 1, "100S", 90.00000928d)))
        println(patternMatcher.typedPatternMatching(Array("1, 1, 1", "1", "90.00000928d")))

        println()

        println("test variableBindingForPatternMatching")
        patternMatcher.variableBindingForPatternMatching("Hello, World!")
        patternMatcher.variableBindingForPatternMatching(UnOp("abs", UnOp("abs", BinOp("x", Number(90), UnOp("-", UnOp("-", Var("x")))))))
        patternMatcher.variableBindingForPatternMatching(BinOp("+", UnOp("abs", BinOp("x", Number(90), UnOp("-", UnOp("-", Var("x"))))), UnOp("abs", UnOp("abs", BinOp("x", Number(90), UnOp("-", UnOp("-", Var("x"))))))))
        patternMatcher.variableBindingForPatternMatching(BinOp("+", UnOp("-", BinOp("x", Number(90), UnOp("-", UnOp("-", Var("x"))))), UnOp("+", UnOp("abs", BinOp("x", Number(90), UnOp("-", UnOp("-", Var("x"))))))))
        patternMatcher.variableBindingForPatternMatching(List(1, "Abd", 1.0003d, 9, true))

        println()

        println("test patternGuardsForPatternMatching")
        println(patternMatcher.patternGuardsForPatternMatching(BinOp("+", UnOp("-", UnOp("-", Number(10))), UnOp("-", UnOp("-", Number(10))))))
        println()
    }

    def evaluateExpressions = {
        val expr1 = UnOp("+", UnOp("-", Number(20)))
        val expr2 = UnOp("abs", UnOp("-", UnOp("+", UnOp("+", UnOp("-", UnOp("abs", Number(90)))))))
        val expr3 = BinOp("*", BinOp("+", Number(5), expr1), Var("x"))
        val expr4 = BinOp("+", expr3, Number(0))
        val expr5 = BinOp("*", BinOp("+", expr1, expr2), Number(1))

        val expr6 = BinOp("*", expr4, expr5)

        val evaluator = new ExprEvaluator
        val expr7 = evaluator.simplifyAll(expr6)
        val expr = evaluator.simplifyAll(BinOp("+", expr6, expr7))

        println(expr6)
        println(expr7)
        println(expr)

    }

    @main def chapterFifteenTests = {
        testCaseClassFacilities
        testNonCaseClassFacilities
        pattentMatchingTests
        evaluateExpressions
        testOptions
        testPatternInVariableDefinitions
        testParticalFunction
    }
}
