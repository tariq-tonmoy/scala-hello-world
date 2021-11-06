package concurrency.chaptereight.actormodel

import akka.actor.*
import akka.event.{Logging, LoggingAdapter}

class HelloActor(val hello: String) extends Actor {
    val logging: LoggingAdapter = Logging(context.system, this)

    def this() = {
        this(HelloActor.defaultHello)
    }

    override def receive: Receive = {
        case `hello` => logging.info(s"received a 'hello' ... $hello")
        case msg => {
            logging.info(s"received and unexpected 'msg' ... $msg")
            context.stop(self)
        }
    }
}

object HelloActor {
    private val defaultHello = "Aloha"

    private def createNewHelloActor(hello: String): HelloActor = {
        new HelloActor(hello)
    }

    def props(hello: String): Props = Props(createNewHelloActor(hello))

    def PropsAlt(hello: String): Props = Props(classOf[HelloActor], hello)

    def propsDefault(): Props = Props[HelloActor]()
}