package concurrency.chapterfour

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object Chapter4 {
    def testFutureSuccessFails = {
        val futureFailed = Future[Int] {
            21 / 0
        }
        val futureSuccess = Future[Int] {
            21 + 21
        }

        Thread.sleep(1000)

        futureFailed.foreach(x => println(s"Failed Future: $x"))
        futureFailed.failed.foreach(x => println(s"Failed futureFailed.failed: $x"))
        futureSuccess.failed.foreach(x => println(s"Success futureSuccess.failed: $x"))
        futureSuccess.foreach(x => println(s"Success Future: $x"))
    }

    def testIOSupportPolling = {
        val lines = IOSupport.readFromFileInFuture("build.sbt")
        println(s"Reading from file: ${lines.isCompleted}")
        Thread.sleep(250)
        println(s"Reading from file: ${lines.isCompleted}")
        println(lines.value.get.get)
    }

    def findKeyword(lines: List[String], keyword: String): String = {
        val foundLines = lines.zipWithIndex.collect {
            case (line, n) if line.contains(keyword) => s"$n: ${line.trim}"
        }.mkString("\n")

        foundLines
    }

    def testSuccessFailedFuture = {
        val future1: Future[Int] = Future {
            21 + 21
        }
        val future2: Future[Int] = Future {
            21 / 0
        }
        val future3: Future[Int] = Future {
            21 + 21
        }
        val future4: Future[Int] = Future {
            21 + 21
        }
        val future5: Future[Int] = Future {
            21 / 0
        }
        val future6: Future[Int] = Future {
            21 + 21
        }
        val future7: Future[Int] = Future {
            21 / 0
        }
        val future8: Future[Int] = Future {
            21 / 0
        }

        Thread.sleep(1000)

        for {
            i <- future1
            j <- future2.failed
        } yield {
            println(s"future1 Succeeded $i, future2 failed $j")
        }

        for {
            i <- future1
            j <- future2
        } yield {
            println(s"future1 succeeded $i; future2 succeeded $j")
        }

        for {
            i <- future1
            j <- future2
        } yield {
            println(s"future1 failed $i; future2 succeeded $j")
        }

        for {
            i <- future1.failed
            j <- future2.failed
        } yield {
            println(s"future1 failed $i; future2 failed $j")
        }

        for {
            i <- future3
            j <- future4
        } yield {
            println(s"future3 succeeded $i; future4 succeeded $j")
        }

        for {
            i <- future5.failed
            j <- future6
        } yield {
            println(s"future5 failed $i; future6 succeeded $j")
        }

        for {
            i <- future7.failed
            j <- future8.failed
        } yield {
            println(s"future7 failed $i; future8 failed $j")
        }
    }

    def testIOSupportCallback = {
        val webPageFuture = IOSupport.readFromUrl("http://www.w3.org/Addressing/URL/url-spec.txt")
        webPageFuture.foreach {
            case lines if lines.length > 0 => {
                println("\nSearch telnet")
                println(findKeyword(lines, "telnet"))
            }
            case _ =>
        }

        webPageFuture.foreach(lines => {
            println("\nSearch Password")
            println(findKeyword(lines, "password"))
        })
    }

    def testTryFuture = {
        val future1: Future[Try[Int]] = Future {
            Try {
                21 + 21
            }
        }
        val future2: Future[Try[Int]] = Future {
            Try {
                21 / 0
            }
        }
        val future3: Future[Try[Int]] = Future {
            Try {
                21 + 21
            }
        }
        val future4: Future[Try[Int]] = Future {
            Try {
                21 + 21
            }
        }
        val future5: Future[Try[Int]] = Future {
            Try {
                21 / 0
            }
        }
        val future6: Future[Try[Int]] = Future {
            Try {
                21 + 21
            }
        }
        val future7: Future[Try[Int]] = Future {
            Try {
                21 / 0
            }
        }
        val future8: Future[Try[Int]] = Future {
            Try {
                21 / 0
            }
        }
        Thread.sleep(1000)
        printSuccessFailure(future1, future2, "future1", "future2")
        printSuccessFailure(future3, future4, "future3", "future4")
        printSuccessFailure(future5, future6, "future5", "future6")
        printSuccessFailure(future7, future8, "future7", "future8")

    }

    def printSuccessFailure(futureA: Future[Try[Int]], futureB: Future[Try[Int]], futureAName: String, futureBName: String) = {
        for {
            i <- futureA
            j <- futureB
        }
        yield {
            (i, j) match {
                case (Success(f1), Failure(f2)) => println(s"printSuccessFailure: $futureAName Success ${f1}; $futureBName Failure ${f2}")
                case (Failure(f1), Success(f2)) => println(s"printSuccessFailure: $futureAName Failure ${f1}; $futureBName Success ${f2}")
                case (Success(f1), Success(f2)) => println(s"printSuccessFailure: $futureAName Success ${f1}; $futureBName Success ${f2}")
                case (Failure(f1), Failure(f2)) => println(s"printSuccessFailure: $futureAName Failure ${f2}; $futureBName Failure ${f2}")
            }
        }
    }

    def testFatalErrors = {
        val future = Future {
            throw new Exception
        }

        val futureFatal = Future {
            throw new InterruptedException
        }

        Thread.sleep(1000)
        val errs = for {
            ex <- future.failed
            fatal <- futureFatal.failed
        }
        yield {
            val exStr = ex match {
                case NonFatal(nonFatal) => "ex nonfatal"
                case _ => "ex fatal"
            }

            val fatalStr = fatal match {
                case NonFatal(nonFatal) => "futureFatal nonFatal"
                case _ => "futureFatal fatal"
            }
            exStr + " " + fatalStr
        }

        println(errs)
    }

    @main def chapterFourTests = {
        testFutureSuccessFails
        testIOSupportPolling
        testIOSupportCallback

        testSuccessFailedFuture
        testTryFuture

        testFatalErrors

        Thread.sleep(Long.MaxValue)
    }
}
