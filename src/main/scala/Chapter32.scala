import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Try, Failure}

object Chapter32 {
    def futureFunc(id: Int, num1: Int, num2: Int, sleep: Int): Future[Int] = {
        val future = Future {
            val res = asynchonousMethod(id, num1, num2, sleep)
            res
        }

        future
    }

    val recursiveFutureFunc: (List[Int] => Future[Unit], List[Future[Int]]) => Future[Unit] = (flatMapFunc, futures) => {
        Future.sequence(futures).flatMap(flatMapFunc)
    }

    val recursiveCallForMap: (List[Int], () => Unit) => Future[Unit] = (p, q) => Future {
        println("Completed: " + p)
        q()
    }

    def asynchonousMethod(id: Int, num1: Int, num2: Int, sleep: Int): Int = {
        for (i <- 1 to 10000000) {
        }
        Thread.sleep(sleep)

        println(s"returning result after sleep in Id: $id; result: ${num1 + num2}")

        id
    }

    def checkCompletionOfFuture(future: Future[Int]): Boolean = future.isCompleted

    def recurseWithFunc(func: (List[Int] => Future[Unit]) => Future[Unit], completionFuture: Future[Int]): List[Int] => Future[Unit] = recursiveCallForMap(_, () => {
        val futureCompleted = checkCompletionOfFuture(completionFuture)
        if (futureCompleted == false) {
            func(recurseWithFunc(func, completionFuture))
        }
        else {
            println("Parent Future Completed")
            Future.successful(())
        }
    })


    def testSuccessFailure = {
        val success = Future {
            21 + 21
        }

        val failure = Future {
            21 / 0
        }

        val successful = Future.successful {
            21 + 21
        }

        try {
            val successful_ex = Future.successful {
                21 / 0
            }
            println("successful_ex: " + successful_ex)
            println("successful_ex.value: " + successful_ex.value)

        } catch {
            case ex: Exception => {
                println("successful_ex Exception: " + ex)
            }
        }

        try {
            val failedThrow = Future.failed {
                // Compilation Error
                // 21 / 0
                // 21 + 21
                throw new ArithmeticException("Divide by zero")
            }

            println("failedThrow: " + failedThrow)
            println("failedThrow.value: " + failedThrow.value)

        } catch {
            case ex: Exception => {
                println("failedThrow Exception: " + ex)
            }
        }

        val failed = Future.failed {
            new ArithmeticException("Divide by zero")
        }

        val successLib = Success {
            21 + 21
        }

        val successTry = Future.fromTry(successLib)

        try {
            val successTry_ex = Future.fromTry(Success {
                21 / 0
            })

            println("successTry_ex: " + successTry_ex)
            println("successTry_ex.value: " + successTry_ex.value)
        }
        catch {
            case ex: Exception => {
                println("successTry_ex Exception: " + ex)
            }
        }


        try {
            val successLib_ex = Success {
                21 / 0
            }
            println("successLib_ex: " + successLib_ex)
            println("successLib_ex.value: " + successLib_ex.value)
        }
        catch {
            case ex: Exception => {
                println("successLib_ex Exception: " + ex)
            }
        }

        try {
            val failureLibThrow = Failure {
                // Compilation Error
                // 21 / 0
                throw new ArithmeticException("Divided by Zero Exception")
            }
            println("failureLibThrow: " + failureLibThrow)
        }
        catch {
            case ex: Exception => {
                println("failureLibThrow Exception: " + ex)
            }
        }

        val failureLib = Failure {
            // Compilation Error
            // 21 / 0
            new ArithmeticException("Divided by Zero Exception")
        }

        try {
            val failureTryThrow = Future.fromTry(Failure(
                // Compilation Error
                // 21 / 0
                throw new ArithmeticException("Divided by Zero Exception")
            ))
            println("failureTryThrow: " + failureTryThrow)
            println("failureTryThrow.value: " + failureTryThrow.value)
        }
        catch {
            case ex: Exception => println("failureTryThrow Exception: " + ex)
        }

        val failureTry = Future.fromTry(Failure(
            // Compilation Error
            // 21 / 0
            new ArithmeticException("Divided by Zero Exception")
        ))

        Thread.sleep(2000)

        println
        println("success: " + success)
        println("success.value: " + success.value)


        println("failure: " + failure)
        println("failure.value: " + failure.value)

        println("successful: " + successful)
        println("successful.value: " + successful.value)

        println("successLib: " + successLib)
        println("successLib.value: " + successLib.value)

        println("successTry: " + successTry)
        println("successTry.value: " + successTry.value)

        println("failed: " + failed)
        println("failed.value: " + failed.value)

        println("failureLib: " + failureLib)
        //  Compilation Error
        //  println("failureLib.value: " + failureLib.value)

        println("failureTry: " + failureTry)
        println("failureTry.value: " + failureTry.value)
    }

    def testFutureSequence = {
        println("\nEntering Future Sequence Tests")

        def future8 = futureFunc(8, 1, 2, 5000)

        def future9 = futureFunc(9, 3, 4, 1000)

        val future10 = futureFunc(10, 0, 0, 30000)

        def future11 = futureFunc(11, 1, 2, 5000)

        def future12 = futureFunc(12, 3, 4, 1000)

        val recurse = recursiveFutureFunc(_, List(future8, future9))

        val recurse11 = recursiveFutureFunc(_, List(future11))

        val recurse12 = recursiveFutureFunc(_, List(future12))

        val res = recurse(recurseWithFunc(recurse, future10))
        val res11 = recurse11(recurseWithFunc(recurse11, future10))
        val res12 = recurse12(recurseWithFunc(recurse12, future10))

        Future.sequence(List(res11, res12)).map(_ => Future.successful(println("Finished")))

        Thread.sleep(40000)

        println("Exiting Future Sequence Tests\n")
    }

    def testBasicFutureApis = {
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
    }


    @main def chapterThirtyTwoTests = {

        testBasicFutureApis
        testFutureSequence
        testSuccessFailure
        Thread.sleep(Long.MaxValue)
    }
}
