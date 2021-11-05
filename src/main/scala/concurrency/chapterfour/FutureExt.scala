package concurrency.chapterfour

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

implicit class FutureExt[T](self: Future[T]) {

    def or(other: Future[T]): Future[T] = {
        val promise: Promise[T] = Promise[T]

        self.onComplete(x => promise.tryComplete(x))
        other.onComplete(x => promise.tryComplete(x))

        promise.future
    }
}
