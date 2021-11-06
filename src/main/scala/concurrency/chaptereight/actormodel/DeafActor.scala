package concurrency.chaptereight.actormodel

import akka.actor.*
import akka.event.{Logging, LoggingAdapter}

class DeafActor extends Actor {
    private val logger: LoggingAdapter = Logging(context.system, this)

    override def receive: Receive = {
        case msg: String => logger.info(s"I cannot hear your message: $msg")
    }

    override def unhandled(message: Any): Unit = super.unhandled(message)
}
