import scala.language.postfixOps;
import scala.collection.mutable;
import scala.io.Source;

object Chapter3 {

    def parameterTests(target: ParenthesisTestTarget): Unit = {
        target(12)
        target("Hello, world!")

        target() = 123
        target() = "WOW"

        target(978) = "Hello There! I'm loving Scala!!!!"

        val x: Array[Int] = Array(1, 2, 3, 4)
        target(990, "Hello there", 3, 4, 5) = 6
    }

    def listTests(target: ParenthesisTestTarget): Unit = {

        val oneTwoThree: List[Int] = List(1, 2, 3);
        val fourFiveSix: List[Int] = List(4, 5, 6);
        val oneTwoThreeFourFiveSixSingle = oneTwoThree ::: fourFiveSix
        val oneTwoThreeFourFiveSixNested = oneTwoThree :: fourFiveSix

        println(oneTwoThree);
        println(fourFiveSix);
        println(oneTwoThreeFourFiveSixSingle);
        println(oneTwoThreeFourFiveSixNested);

        println("Nested in List: ")
        println(oneTwoThreeFourFiveSixNested(0))
        println("Last element in Nested List: ")
        println(oneTwoThreeFourFiveSixNested(3))


        /* Cannot do it
         * val newListFromNestedList: List[Any] = oneTwoThreeFourFiveSixNested
         * println(newListFromNestedList.apply(0))
         * oneTwoThree += 90
        **/

        val num: Int = 7;
        val sevenFourFiveSix = num :: fourFiveSix
        println(sevenFourFiveSix)

        //target :: "Left" //will not work
        //Cons operator
        "Right" :: target

        val consList = 1 :: 2 :: 3 :: Nil

        println(consList)
    }

    def tuppleTests(target: ParenthesisTestTarget): Unit = {
        val tupple = (1, "Two", 3.0d, '4')
        println(tupple._1)
        println(tupple._2)
        println(tupple._3)
        println(tupple._4)
    }

    def scalaSetTests(target: ParenthesisTestTarget): Unit = {
        val jetSet: Set[String] = Set("Boeing", "Airbus")
        val newJetSet = jetSet + "Lear"

        println("Console println in foreach")
        newJetSet.foreach(jet => Console println jet)

        println("println in foreach without parameter")
        newJetSet.foreach(println)

        println("println inside target")
        newJetSet.foreach(jet => target printInForEach jet)

        println("println inside target without parameter")
        newJetSet.foreach(target printInForEach)

        println(newJetSet toIndexedSeq 0)

        var mutMovieSet: mutable.Set[String] = mutable.Set[String]();
        mutMovieSet += "Shrek"
        mutMovieSet += "Batman"
        mutMovieSet += "The Lord of the Rings"
        mutMovieSet += "Abc"
        mutMovieSet += "Shrek2"
        mutMovieSet += "."
        mutMovieSet += "1"

        mutMovieSet.foreach(target printInForEach)
    }

    def scalaMapTests(target: ParenthesisTestTarget): Unit = {
        val romanNumerals: Map[Int, String] = Map[Int, String](1 -> "I", 2 -> "II", 3 -> "III", 4 -> "IV", 5 -> "V")
        romanNumerals.foreach(println)

        val mutableMap: mutable.Map[Int, String] = mutable.Map[Int, String](1 -> "Hello", 2 -> ", ")
        mutableMap += (3 -> "World")
        mutableMap += (4 -> "!\n")
        mutableMap.foreach(map => print(mutableMap(map._1)))
    }

    def testFileIo() = {

        val usrDir = System.getProperty("user.dir") + "\\src\\main\\scala"
        var file = usrDir + "\\Chapter3.scala"

        val linesWithCharCount = for {
            line <- Source.fromFile(file).getLines()
        }
        yield {
            var lineTupple = (line.length, line)
            lineTupple
        }

        val lineWithCountSeq = linesWithCharCount.toIndexedSeq

        val listTraversal: TraversibleList = TraversibleList(lineWithCountSeq.map(x => x._1.asInstanceOf[Double]).toSeq: _*)
        var maxCharCount = listTraversal.reduceLeft((left, right) => if (left > right) left else right).asInstanceOf[Int]
        var maxCountLen = maxCharCount.toString.length

        for (tupple <- lineWithCountSeq) {
            val padding = " " * (maxCountLen - tupple._1.toString.length)
            val line = padding + tupple._1 + " | " + tupple._2
            Console println line
        }
    }

    @main def chapterThreeTests(): Unit = {

        val target: ParenthesisTestTarget = new ParenthesisTestTarget

        parameterTests(target)

        listTests(target)

        tuppleTests(target)

        scalaSetTests(target)

        scalaMapTests(target)

        testFileIo()
    }
}
