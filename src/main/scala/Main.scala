import scala.collection.immutable as NewImmutable;
def checkUnit(): Unit = {
    val aUnit: Unit = ();
    println("Inside a unit");
    aUnit
}

def conciseForLoopAddTwo(arr: Array[Double]): Array[Double] = for i <- arr yield i + 2;

def printResultsInWhileLoop(resArr: Array[Double], addedResArr: Array[Double]): Unit = {
    println("Printing Results in While Loop: ")
    val arrLen: Int = resArr.length;
    var i: Int = 0;
    while (i < arrLen) {
        println(resArr(i))
        println(addedResArr(i))
        println()
        i += 1;
    }
}


def sumArrayElements(arr1: Array[Int], arr2: Array[Double]): Array[Double] = {
    val resArr: Array[Double] = for {
        intVal <- arr1
        doubleVal <- arr2
        if intVal > 10
    } yield {
        println("intVal: " + intVal)
        println("doubleVal: " + doubleVal)
        println()
        val resVal: Double = intVal + doubleVal

        resVal
    }

    return resArr
}

def testForLoop(): Unit = {
    println("Hello world!")
    println(msg)
    val aUnit: Unit = checkUnit();

    val intArr: Array[Int] = Array(10, 20, 30)
    val doubleArr: Array[Double] = Array(30.0, 10.0, -90.0)
    val resVal: Array[Double] = sumArrayElements(intArr, doubleArr)
    val addResVal: Array[Double] = conciseForLoopAddTwo(resVal);
    println("Printing Results: ")
    val arrLen: Int = resVal.length;
    for (i <- 0 to arrLen - 1) {
        println(resVal(i))
        println(addResVal(i))
        println()
    }

    printResultsInWhileLoop(conciseForLoopAddTwo(resVal), conciseForLoopAddTwo(addResVal));
}


def printRightToLeftLower(len: Int, char: Char): Unit = {
    for {
        i <- 0 to len - 1
        j <- 0 to len
    } {
        val res: Char = if j == len then '\n' else if (i + j < len - 1) then ' ' else char;
        print(res);
    }
}

def printRightToLeftUpper(len: Int, char: Char): Unit = {
    val multiDimArr: NewImmutable.IndexedSeq[NewImmutable.IndexedSeq[Char]] = for {
        i <- 0 to len - 1
    }
    yield {
        val charAr: NewImmutable.IndexedSeq[Char] = for {
            j <- 0 to len
        }
        yield {
            val res: Char = if j == len then '\n' else if (i + j < len) then char else ' ';
            res
        }

        charAr
    }
}

def printDiagram(): Unit = {
    printRightToLeftLower(5, '*');
    printRightToLeftUpper(5, '*');

    println();
}

def multiplyInLoop(x: Int, y: Int): Int = x * y;

def multiplyInLoop(nums: Int*): Int = {
    var product: Int = 1;
    for (num <- nums) {
        product = multiplyInLoop(product, num);
    }

    product
}

def multiplyInRecursion(nums: Int*): Int = {
    val res: Int = if (nums.length == 1) {
        nums.last
    }
    else {
        val res2 = multiplyInLoop(multiplyInRecursion(nums.take(nums.length - 1): _*), nums(nums.length - 1))
        res2
    }

    res
}

def testTypeArgument(num1: Int, num2: Int, strings: String*): Unit = {

}

@main def hello: Unit = {
    val target: ParenthesisTestTarget = new ParenthesisTestTarget()
    target(10)

    //    val productFromLoop = multiplyInLoop(3, 4, 5, 6, 7);
    //    println(productFromLoop);
    //
    //    val productFromRecursion = multiplyInRecursion(3, 4, 5, 6, 7);
    //    println(productFromRecursion);
    //    Int

}


def msg = "I was compiled by Scala 3. :)"
