import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

object Chapter32 {
    val futureFunc: (Int, Int, Int, Int) => Future[Int] = (id, num1, num2, sleep) => {
        val future = Future {
            val res = asynchonousMethod(id, num1, num2, sleep)
            res
        }

        future
    }

    def asynchonousMethod(id: Int, num1: Int, num2: Int, sleep: Int): Int = {
        for (i <- 1 to 10000000) {
        }
        println(s"Going into sleep in Id: $id")

        Thread.sleep(sleep)

        println(s"returning result after sleep in Id: $id")
        num1 + num2
    }

    def testFutureSequence = {
        println("\nEntering Future Sequence Tests")

        val future8 = futureFunc(8, 1, 2, 1000)
        val future9 = futureFunc(9, 3, 4, 500)

        Future.sequence(List(future8, future9)).flatMap(x => Future.successful(x.sum))


        println("Exiting Future Sequence Tests\n")
    }

    @main def chapterThirtyTwoTests = {
        val future1 = futureFunc(1, 10, 20, 1000)
        val future1_1 = futureFunc(11, 30, 20, 3000)
        val future2 = futureFunc(_, _, 20, 500)

        val future3 = future1.flatMap(num1 => future2(3, num1))

        val res1 = for {
            x <- future1
            y <- future3
        } yield {
            println(s"Future 1 and Future 3 are complete ${x + y}")
            x + y
        }
        println(res1)
        Console.println("After Invoking Future")
        println(future3.value)

        Thread.sleep(2000)
        println(future3.value)
        println(res1.value.get)

        val future4 = futureFunc(4, 10, 20, 1000)
        val future5 = futureFunc(5, 10, 20, 500)
        val res45 = for {
            x <- future4
            y <- future5
        } yield {
            x + y
        }

        Thread.sleep(1100)
        println(s"Should get result of future4 + future 5 = ${res45}")

        val res67 = for {
            x <- futureFunc(5, 10, 20, 1000)
            y <- futureFunc(7, 10, 20, 500)
        } yield {
            x + y
        }

        Thread.sleep(1100)
        println(s"Should NOT get result of future6 + future 7 = ${res67}")

        Thread.sleep(500)
        println(s"Should get result of future6 + future 7 = ${res67}")

        testFutureSequence

        Thread.sleep(Long.MaxValue)
    }
}
