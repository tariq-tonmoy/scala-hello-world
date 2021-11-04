package concurrency.chapterthree

import scala.concurrent._
import java.util.concurrent._

class TestExecutors(parallelism: Int) {
    private val pool = new ForkJoinPool(parallelism)
    private val executerCtx = ExecutionContext.fromExecutorService(pool)

    def execute(executables: List[() => Unit]) = {
        for (func <- executables) {
            executerCtx.execute(new Runnable {
                override def run(): Unit = func()
            })
        }

        executerCtx.shutdown()
        executerCtx.awaitTermination(30, TimeUnit.SECONDS)
        println("execution completed")
    }
}
