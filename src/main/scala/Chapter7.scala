import java.io.File
import scala.io.Source
import rationals._

object Chapter7 {

    def calculateSingleElementInMultiplyTable(row: Int, col: Int, padding: Int): String = {
        val multiplication = ((row + 1) * (col + 1)).toString
        val paddingStr = " " * (padding - multiplication.length)
        s"${paddingStr}${multiplication}"
    }

    def generateRow(row: Int, col: Int, padding: Int, colLength: Int, generatedRow: String): String = {
        val res = if (col < colLength) {
            var multiplicationRes = calculateSingleElementInMultiplyTable(row, col, padding)
            val changedRow = generatedRow + multiplicationRes
            val generate = generateRow(row, col + 1, padding, colLength, changedRow)
            generate
        }
        else {
            generatedRow + "\n"
        }

        res
    }

    def generateTable(rowLength: Int, colLength: Int, padding: Int): IndexedSeq[String] = {
        val table = for {
            row <- 0 until rowLength
        } yield {
            var rowString = generateRow(row, 0, padding, colLength, "")
            rowString
        }

        table
    }

    def testFinally(n: Int): Unit = {
        try {
            throwExceptions(n)
        }
        catch {
            case ex: NotImplementedError => {
                println(ex)
            }
            case ex: RuntimeException => {
                println(ex)
            }
        }
        finally {
            println("Inside finally")
        }
    }

    def testFinallyWithReturn(n: Int): String = {
        try {
            throwExceptions(n)
            return "Success"
        }
        catch {
            case ex: NotImplementedError => {
                println(ex)
                return "NotImplementedError"
            }
            case ex: RuntimeException => {
                println(ex)
                return "RuntimeException"
            }
        }
        finally {
            println("Inside finally")
            return "From Finally"
        }
    }

    def testFinallyWithoutReturn(n: Int): String = {
        try {
            throwExceptions(n)
            "Success"
        }
        catch {
            case ex: NotImplementedError => {
                println(ex)
                "NotImplementedError"
            }
            case ex: RuntimeException => {
                println(ex)
                "RuntimeException"
            }
        }
        finally {
            println("Inside finally")
            "From Finally"
        }
    }

    def throwExceptions(n: Int): Int = {
        val evenNumber = if (n % 2 == 0 && n >= 0) {
            n
        }
        else if (n < 0) {
            throw new NotImplementedError("No even number less than zero")
        }
        else {
            throw new RuntimeException("Cannot evaluate")
        }

        evenNumber
    }

    def handleExceptionsLevelOne(n: Int): Unit = {
        val res = try {
            val number = handleExceptionsLevelZero(n)
            number
        }
        catch {
            case ex: Exception => {
                val oneTwoThree: List[Int] = List(1, 2, 3);
                oneTwoThree
            }
        }

        res match {
            case _res: Int => {
                println("Int")
                println(_res)
            }
            case _res: List[Int] => {
                println("List[Int]")
                _res.asInstanceOf[List[Int]].foreach(x => println(x))
            }
            case _res: Rational => {
                println("Rational")
                println(_res.asInstanceOf[Rational].toString())
            }
        }
    }

    def handleExceptionsLevelZero(n: Int) = {
        val res = try {
            val number = throwExceptions(n);
            number;
        }
        catch {
            case ex: NotImplementedError => {
                val rational__5_4: Rational = new Rational(-5, 4)
                rational__5_4
            }
        }

        res;
    }

    def fileLines(file: File) = Source.fromFile(file).getLines.toList

    def grep(pattern: String, files: Array[File]) = {
        for {
            file <- files
            if file.getName.endsWith(".scala")
            line <- fileLines(file)
            trimmedLine = line.trim
            if trimmedLine.matches(pattern)
        } {
            println(file.getName + ": " + trimmedLine)
        }
    }

    def getFilesFromForLoop(pattern: String, files: List[File]): List[String] = {
        var res = for {
            file <- files
            if file.getName.endsWith(".scala")
            line <- fileLines(file)
            trimmedLine = line.trim
            if trimmedLine.matches(pattern)
        }
        yield {
            trimmedLine
        }

        res
    }

    def trimLine(str: String) = str.trim

    def forLoopInList(list: List[String], pattern: String) = {
        val res = for {
            line <- list
            if line.matches(pattern)
            trimmedLine = trimLine(line)
        } yield {
            line
        }

        res
    }

    @main def chapterSevenTests() = {
        var dir = System.getProperty("user.dir") + "\\src\\main\\scala"

        val filesInDir = (new File(dir)).listFiles
        val numIterator = 0 until filesInDir.length

        grep(".*gcd.*", filesInDir)


        handleExceptionsLevelOne(10)
        handleExceptionsLevelOne(-10)
        handleExceptionsLevelOne(11)

        testFinally(10)
        testFinally(-1)
        testFinally(1)

        println("\n\nTesting testFinallyWithReturn:")
        println(s"No error: ${testFinallyWithReturn(10)}")
        println(s"NotImplementedError: ${testFinallyWithReturn(-1)}")
        println(s"RuntimeException: ${testFinallyWithReturn(1)}")

        println("\n\nTesting testFinallyWithoutReturn:")
        println(s"No error: ${testFinallyWithoutReturn(10)}")
        println(s"NotImplementedError: ${testFinallyWithoutReturn(-1)}")
        println(s"RuntimeException: ${testFinallyWithoutReturn(1)}")

        var multiplicationTable = generateTable(10, 10, 4)

        multiplicationTable.foreach(x => Console print x)
    }
}
