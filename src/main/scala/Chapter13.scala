import scala.collection.mutable.{Seq => MutableSeq, Map => MutableMap}

object Chapter13 {

    def testMutableImports = {
        val immutableMap = Map[Char, Int]('x' -> 1, 'y' -> 2, 'z' -> 3)
        val mutableMap: MutableMap[Char, Int] = MutableMap[Char, Int]('a' -> 9, 'b' -> 8, 'c' -> 7)

        mutableMap.addOne('d', 6)
        mutableMap += ('e' -> 5)

        mutableMap('a') = 100
        //immutableMap('x') = 900
        //immutableMap += ('a' -> 900)

        println(s"mutableMap: ${mutableMap.mkString(", ")}")
        println(s"immutableMap: ${immutableMap.mkString(", ")}")
    }

    def testEasyAccessImport = {
        import bobsdelights.Fruits

        val apple: Fruits = Fruits.Apple
        testAccessRandomFieldsFromImport(apple)
    }

    def testOnDemandImport = {
        import bobsdelights._

        val pear: Fruits = Fruits.Pear
        testAccessRandomFieldsFromImport(pear)
    }

    def testStaticImport = {
        import bobsdelights.Fruits._

        val orange = Orange
        testAccessRandomFieldsFromImport(orange)
    }

    def testFlexibleImports = {
        def testSelectiveImport = {
            import bobsdelights.Fruits.{Apple, Orange}
            val apple = Apple
            val orange = Orange
            //val pear = Pear

            testAccessRandomFieldsFromImport(apple)
            testAccessRandomFieldsFromImport(orange)
        }

        def testRenamedSelectiveImport = {
            import bobsdelights.Fruits.{Apple => Macintosh, Orange}
            //val apple = Apple
            val macintoshApple = Macintosh
            val orange = Orange
            //val pear = Pear

            testAccessRandomFieldsFromImport(macintoshApple)
            testAccessRandomFieldsFromImport(orange)
        }

        def testRenamedAndOtherMembersImport = {
            import bobsdelights.Fruits.{Apple => Macintosh, _}
            //val apple = Apple
            val macintoshApple = Macintosh
            val orange = Orange
            val pear = Pear

            testAccessRandomFieldsFromImport(macintoshApple)
            testAccessRandomFieldsFromImport(orange)
            testAccessRandomFieldsFromImport(pear)
        }

        def testRenamedAndOtherMembersExceptOneImport = {
            import bobsdelights.Fruits.{Orange => _, Apple => Macintosh, _}
            //val apple = Apple
            val macintoshApple = Macintosh
            //val orange = Orange
            val pear = Pear

            testAccessRandomFieldsFromImport(macintoshApple)
            testAccessRandomFieldsFromImport(pear)
        }

        testSelectiveImport
        testRenamedSelectiveImport
        testRenamedAndOtherMembersImport
        testRenamedAndOtherMembersExceptOneImport
    }

    def testAccessRandomFieldsFromImport(fruits: bobsdelights.Fruits) = {
        import fruits._
        println(s"$name: $color")
    }

    def testPackageImport = {
        testEasyAccessImport
        testOnDemandImport
        testStaticImport
    }

    def testPackageObjects = {
        import bobsdelights.showFruits
        import bobsdelights.Fruits._

        for (fruit <- menu) {
            showFruits(fruit)
        }
    }

    @main def chapterThirteenTests = {
        testPackageImport
        testFlexibleImports
        testMutableImports

        testPackageObjects
    }
}
