package expressions

import Math.{E, PI}

class PatternMatcher {

    val pi = PI
    val e = E

    def constantPatternMatching(x: Any): String = x match {
        case "Hello" => "String"
        case 5 => "Int"
        case Nil => "Empty List"
        case true => "Boolean"
        case PI => "This is pi"
        case _ => "Unknown"
    }

    def variableAsConstantPatternMatching(x: Double, patternMatcher: PatternMatcher) = {
        val piE = PI + E
        val TestValue = 1.414292654
        x match {
            case this.pi => s"this is pi variable: $pi"
            case TestValue => s"this is TestValue: $TestValue"
            case `piE` => s"this is pie: $piE"
            case patternMatcher.e => s"this is E: ${patternMatcher.e}"
            case _ => "Nothing Matches"
        }
    }

    def variablePatternMatching(x: Double) = {
        val pi2 = PI
        x match {
            case pi => s"this is pi variable: $pi"
            // Compilation Error: Capital Letter at the beginning
            // case Hello => s"this is pi variable: $Hello"
            // All are unreachable case down from here
            case pi2 => "this is pi variable"
            case _ => "Nothing"
        }
    }

    def sequencePatternMatching(expr: Any) = {
        expr match {
            case List(0, _*) => println("Got list with 0")
            case (a, b, c) => println(s"Got Tuple: $a, $b, $c")
            case _ => println("Got nothing")
        }
    }

    def typedPatternMatching(expr: Any): Int = {
        expr match {
            case str: String => {
                println("It's string")
                str.length
            }
            case list: List[_] => {
                println("It's List")
                list.foreach(x => println(x))
                list.size
            }
            case map: Map[_, _] => {
                println("It's Map")
                map.foreach(x => println(x))
                map.size
            }
            case strArr: Array[String] => {
                println("It's String array")
                // Compilation Error
                // expr.size
                strArr.size
            }
            case doubleArr: Array[Double] => {
                println("It's Double array")
                doubleArr.size
            }
            case intArr: Array[Int] => {
                println("It's Int array")
                intArr.size
            }
            case arr: Array[_] => {
                println("It's Generic array")
                arr.foreach(x => println(x))
                arr.size
            }
            case _ => {
                println("Doesn't match")
                -1
            }
        }
    }

    def variableBindingForPatternMatching(expr: Any) = {
        expr match {
            case UnOp("abs", e@UnOp("abs", _)) => {
                println(e)
            }
            case BinOp("+", e@UnOp("-", _), f@UnOp("+", _)) => {
                println(e)
                println(f)
            }
            case List(1, e@_*) => {
                println(e)
            }
            case _ => println("Doesn't match")
        }
    }

    def patternGuardsForPatternMatching(expr: Expr) = {

        expr match {
            case BinOp(op: String, x: Expr, y: Expr) if x == y && op == "+" => BinOp("*", x, Number(2))
            case _ => expr
        }
    }
}
