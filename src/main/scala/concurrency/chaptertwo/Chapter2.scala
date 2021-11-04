package concurrency.chaptertwo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.collection.mutable.{Queue => MutableQueue}

object Chapter2 {

    def increment(var1: Int) = var1 + 1

    def testConcurrentVariableAccessFuture = {
        val val1 = {
            println("inside val1")
            90
        }

        var var1 = {
            println("Inside var1")
            100
        }
        val queue: MutableQueue[Int] = MutableQueue()
        for (i <- 0 until 150000) {
            Future {
                val x = val1
                var1 = var1 + 1
                println(var1)
                queue.enqueue(var1)
            }
        }
        for (i <- 0 until 150000) {
            Future {
                val x = val1
                var1 = var1 + 1
                println(var1)
                queue.enqueue(var1)
            }
        }

        val x = val1
        var1 = var1 + 1
        println(var1)


        Thread.sleep(10000)
        println("10 seconds uo")
        println(queue.length)
        println(queue.distinct.length)

        for (i <- 0 until queue.length) {
            Future {
                queue.dequeue()
            }
        }

        Thread.sleep(5000)
        println(queue.length)

        val queue2 = MutableQueue[Int]()
        for (i <- 0 until 300000) {
            Future {
                //queue2.synchronized {
                queue2.enqueue(i)
                //}
            }
        }

        Thread.sleep(5000)
        println("NewQueue lenght")
        println(queue2.length)
        println(queue2.distinct.length)
    }

    def testConcurrentVariableAccessThread = {
        println("\ninside testConcurrentVariableAccessThread")
        val val1 = {
            println("inside val1")
            90
        }

        var var1 = {
            println("Inside var1")
            100
        }

        for (i <- 0 until 30000) {
            val th = new Thread {
                override def run(): Unit = {
                    val x = val1
                    val var11 = increment(var1)
                    var1 = var11
                    println(var1)
                }
            }
            th.start()
        }

        val x = val1
        val var11 = increment(var1)
        var1 = var11
        println(var1)
    }

    def testVolatileVars = {
        @volatile var flag = false
        flag = true
        for (i <- 0 until 3000) {
            new Thread {
                override def run(): Unit = {
                    while (flag && i <= 1500) {
                        Thread.sleep(1000)
                        println(i)
                    }
                    println(i)
                    flag = false
                }
            }.start()

            println(i)
        }

        while (flag) {}

        println("Volatile Ends")
    }

    def testVolatileUsage = {
        var x: Int = -1
        var y: Int = -1
        var count: Int = 0
        while ((x == 1 && y == 1) == false && count <= 100000) {
            x = -1
            y = -1
            var a: Boolean = false
            var b: Boolean = false

            val tax = new Thread {
                override def run(): Unit = {
                    a = true
                    x = if (b) 0 else 1
                }
            }

            val tby = new Thread {
                override def run(): Unit = {
                    b = true
                    y = if (a) 0 else 1
                }
            }

            tax.start()
            tby.start()

            tax.join()
            tby.join()
            if (x == 1 && y == 1) {
                println(s"NON VOLATILE Count: $count; x: $x; y: $y")
            }

            count += 1
        }
        println(s"NON VOLATILE Ended: x: $x; y: $y")

        count = 0
        x = -1
        y = -1
        while ((x == 1 && y == 1) == false && count <= 100000) {
            x = -1
            y = -1
            @volatile var a: Boolean = false
            @volatile var b: Boolean = false

            val tax = new Thread {
                override def run(): Unit = {
                    a = true
                    x = if (b) 0 else 1
                }
            }

            val tby = new Thread {
                override def run(): Unit = {
                    b = true
                    y = if (a) 0 else 1
                }
            }

            tax.start()
            tby.start()

            tax.join()
            tax.join()
            tby.join()
            if (x == 1 && y == 1) {
                println(s"Volatile Count: $count; x: $x; y: $y")
            }

            count += 1
        }
        println(s"VOLATILE Ended: x: $x; y: $y")
    }

    @main def chapterTwoTests = {
        testConcurrentVariableAccessFuture
        testConcurrentVariableAccessThread
        testVolatileVars
        testVolatileUsage

        Thread.sleep(Long.MaxValue)

    }
}
