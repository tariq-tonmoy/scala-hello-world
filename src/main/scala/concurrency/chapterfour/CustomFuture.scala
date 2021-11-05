package concurrency.chapterfour

import scala.concurrent._
import java.util.concurrent._
import scala.util.control._
import scala.concurrent.{Future => Future}

object CustomFuture {
    private val pool = new ForkJoinPool(8)

    private val executionCtx = ExecutionContext.fromExecutorService(pool)

    def futureImpl[T](execution: => T): Future[T] = {
        val promise: Promise[T] = Promise[T]

        executionCtx.execute {
            new Runnable {
                override def run(): Unit = {
                    try {
                        promise.success(execution)
                    }
                    catch {
                        case NonFatal(ex) => promise.failure(ex)
                    }
                }
            }
        }

        promise.future
    }

    def shutdown = {
        executionCtx.shutdown()
        executionCtx.awaitTermination(3, TimeUnit.SECONDS)
        println("executionCtx terminated")
    }
}
