import scala.language.postfixOps;

class ParenthesisTestTarget {
    def apply(num: Int): Unit = {
        Console println "Inside Num Apply: " + num
    }

    def apply(str: String): Unit = {
        Console println "Inside Str Apply: " + str
    }

    def update(str: String): Unit = {
        Console println "Inside Str Update: " + str
    }

    def update(num: Int): Unit = {
        Console println "Inside Num Update: " + num
    }

    def update(num: Int, str: String): Unit = {
        Console println "Inside Num and str Update: " + num + " " + str
    }

    def update(num1: Int, str1: String, nums: Int*): Unit = {
        nums.foreach((num: Int) => {
            Console println num
        })
    }

    def ::(side: String): Unit = println("Cons Operand: " + side)

    def printInForEach(str: String): Unit = {
        println("Pass implicit parameter from for each: " + str)
    }
}


