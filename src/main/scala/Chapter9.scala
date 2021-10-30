object Chapter9 {

    val strings: List[String] = List("   Hello, World!  ", "Hello, 12 World!  ", "Hello,", " World!", "H e l l o ,  W o r l d ! ")

    val firstList: TraversibleList = TraversibleList(3, -1, 4, 1, 5, 9, 2, 6, 5, 4, 0)
    val secondList: TraversibleList = TraversibleList(3, 10, 4, 1, 5, 9, 2, 6, 0, 4, 1, 10)

    val traversibleLists: List[TraversibleList] = List(
        TraversibleList(3, -1, 4, 1, 5, 9, 2, 6, 5, 4),
        TraversibleList(3, -1, 4, 1, 5, 9, 2, 6, 5, 40),
        TraversibleList(3, -1, 4, 1, 5, 9, 2, 6, 5, 4),
        TraversibleList(3, -10, 4, 1, 5, 9, 2, 6, 5, 4),
        TraversibleList(3, -1, 4, 1, 5, 9, 2, 6, 5, 4))

    def matchEndsComparer: (String, String) => Boolean = _.endsWith(_)

    def matchContains: (String, String) => Boolean = _.contains(_)

    def matchPattern: (String, String) => Boolean = _.matches(_)

    def listMinComparer: (TraversibleList, TraversibleList) => Boolean = _.hasMinValue(_)

    def listMaxComparer: (TraversibleList, TraversibleList) => Boolean = _.hasMaxValue(_)

    def testFunctionValuesAsArgument(query: String, matcher: (String, String) => Boolean, testStrings: String*): Seq[String] = {
        var res = for {
            str <- testStrings
            trimmedStr = str.trim
            if (matcher(trimmedStr, query))
        }
        yield {
            trimmedStr
        }

        res;
    }

    def printMatchedString(title: String, matchedStrings: Seq[String]) = {
        println(title)
        for (str <- matchedStrings) println(str)
        println
    }

    def matchString(queryAtEnd: String, queryAtContains: String, queryAtRegex: String, testStrings: String*) = {
        val partialMatchFunc: (String, (String, String) => Boolean) => Seq[String] = testFunctionValuesAsArgument(_, _, testStrings: _*)
        val matchedStringsWithEnds: Seq[String] = partialMatchFunc(queryAtEnd, matchEndsComparer)
        val matchedStringsWithContains: Seq[String] = partialMatchFunc(queryAtContains, matchContains)
        val matchedStringsWithRegex: Seq[String] = partialMatchFunc(queryAtRegex, matchPattern)

        printMatchedString("Matched with ends", matchedStringsWithEnds)
        printMatchedString("Matched with contains", matchedStringsWithContains)
        printMatchedString("Matched with pattern", matchedStringsWithRegex)

    }

    def compareLists(currentIdx: Int,
                     lists: List[TraversibleList],
                     comparer: (TraversibleList, TraversibleList) => Boolean,
                     currentList: TraversibleList = null): TraversibleList = {
        val partialListComparer: TraversibleList => TraversibleList = compareLists(currentIdx + 1, lists, comparer, _)
        val res = if (currentIdx == lists.length - 1 && currentList == null) {
            lists(currentIdx)
        }
        else if (currentIdx == 0) {
            partialListComparer(lists(currentIdx))
        }
        else if (currentIdx >= lists.length) {
            currentList
        }
        else {
            val compareRes = comparer(currentList, lists(currentIdx))
            if (compareRes == true) partialListComparer(currentList) else partialListComparer(lists(currentIdx))
        }

        res
    }


    def testCurrying = {
        def first(x: Int): Int => Int = x + _

        def second(tempFirst: Int => Int, y: Int): Int = tempFirst(y)

        val firstFunc: Int => Int = first(10)

        for (i <- 1 to 10) {
            println(second(firstFunc, i))
        }

        def addTwoNumbers(x: Int)(y: Int): Int = x + y

        println {
            addTwoNumbers(2)(4)
        }

        val newList = firstList.operateOnList(secondList, 90) {
            (num1: Double, num2: Double) => {
                num1 / (if (num2 == 0.0) 1.0 else num2)
            }
        }

        println(newList.toString)
    }

    def testByNameParameters = {
        def printUsingByNameParameter(traversibleListPredicate: => TraversibleList) = {
            println("Inside func with By-Name parameter")
            println(traversibleListPredicate.toString());
        }

        def printUsingParameter(traversibleListPredicate: TraversibleList) = {
            println("Inside func with regular parameter")
            println(traversibleListPredicate.toString());
        }

        def getTraversibleList(numbers: Double*): TraversibleList = {
            println("Inside function to produce traversible list")
            TraversibleList(numbers: _*)
        }

        Console println "Printing using byname parameter"
        printUsingByNameParameter(getTraversibleList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        println

        Console println "Printing using regular parameter"
        printUsingParameter(getTraversibleList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    }

    @main def chapterNineTests(): Unit = {
        matchString("d!", "rld", ".*W.*d.*", strings: _*)

        val partialCompareLists = compareLists(0, traversibleLists, _)
        val minRes = partialCompareLists(listMinComparer)
        val maxRes = partialCompareLists(listMaxComparer)
        println(minRes.toString)
        println(maxRes.toString)

        val singleTraversibleList: List[TraversibleList] = List(firstList)
        val minSingleRes = compareLists(0, singleTraversibleList, listMinComparer)
        println(minSingleRes.toString)

        testCurrying

        testByNameParameters
    }
}
