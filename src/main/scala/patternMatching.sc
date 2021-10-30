import scala.runtime.Nothing$

val amount: Int = 98
amount match {
    case _am if _am >= 0 && _am <= 50 => println("Between 0 and 50: $" + _am)
    case _am1 if _am1 > 50 && _am1 <= 100 => println("Between 51 and 100: $" + _am1)
    case _am2 => {
        println("nevermind: $" + _am2)
    }
}

def isDivisibleBy3Or5(x: Int): Boolean = x % 5 == 0 || x % 3 == 0

val arr: Array[Int] = Array(1, 2, 98, 4, 52, 98)
val results: Array[Int] = arr match {
    case _arr if _arr.exists(x => isDivisibleBy3Or5(x)) => {
        val _res: Array[Int] = _arr.filter(x => isDivisibleBy3Or5(x));
        _res
    }
    case _arr if _arr.exists(x => x == amount) => {
        val _res: Array[Int] = _arr.filter(x => x == amount).map(x => x + 90)
        _res
    }
    case _arr => {
        val _res: Array[Int] = Array()
        _res
    }
}
