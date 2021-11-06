package concurrency.chaptereight.actormodel

import akka.actor.*
import akka.event.{Logging, LoggingAdapter}

import scala.annotation.tailrec
import scala.io.Source

class DictionaryActor extends Actor {
    val logger: LoggingAdapter = Logging(context.system, this)

    @tailrec private def createDictionary(wordIterator: Iterator[String], dictionary: Set[String]): Set[String] = {
        if (!wordIterator.hasNext) {
            dictionary
        }
        else {
            val dictionaryWithNewWord = dictionary + wordIterator.next()
            createDictionary(wordIterator, dictionaryWithNewWord)
        }
    }

    def uninitialized: Receive = {
        case DictionaryActor.Init(path) => {
            val userDir = System.getProperty("user.dir")
            val wordStream = Source.fromFile(userDir + path)
            val words = wordStream.getLines()
            val dictionary = createDictionary(words, Set.empty[String])
            context.become(initialized(dictionary))
            logger.info(s"Initialized dictionary with path $path")
        }
    }

    def initialized(dictionary: Set[String]): Receive = {
        case DictionaryActor.IsWord(word) => {
            dictionary.find(x => x.equalsIgnoreCase(word)) match {
                case Some(value) => {
                    logger.info(s"`$value` Found!")
                }
                case _ => logger.info("No Match!")
            }

            context.become(initialized(dictionary))
        }
        case DictionaryActor.End() => {
            logger.info("Ending Session")
            context.become(uninitialized)
        }
    }

    override def receive: Receive = uninitialized

    override def unhandled(message: Any): Unit = {
        logger.info(s"Cannot handle message: ${message}")
    }
}

object DictionaryActor {
    case class Init(path: String)

    case class IsWord(word: String)

    case class End()
}