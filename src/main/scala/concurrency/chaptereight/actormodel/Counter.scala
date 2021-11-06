package concurrency.chaptereight.actormodel

import akka.actor.*
import akka.event.{Logging, LoggingAdapter}

class Counter(private val counter: Int) extends Actor {
    require(counter > 0)

    private val logging: LoggingAdapter = Logging(context.system, this)


    private def counting(num: Int): Receive = {
        case "count" => {
            val doneLog: Option[String] = if (num == 1) {
                context.become(done)
                Some("I am becoming done!")
            }
            else {
                context.become(counting(num - 1))
                None
            }

            val emptyStr = ""
            val log = s"decreasing value from $num to ${num - 1}. ${if (doneLog.isDefined) doneLog.get else emptyStr}"
            logging.info(log)
        }
    }

    private def done: Receive = {
        case "count" => logging.info("I am done counting")
    }

    override def receive: Receive = counting(counter)
}

object Counter {
    def getProp(num: Int): Props = Props(classOf[Counter], num)
}

