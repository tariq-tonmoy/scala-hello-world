package concurrency.chaptereight.actormodel

import akka.actor.{ActorRef, ActorSystem, Props}

object BasicActorSystem {
    private lazy val basicActorSystem = ActorSystem("BasicActorSystem")

    def testBasicActorSystem(): Unit = {
        val actorRef: ActorRef = basicActorSystem.actorOf(HelloActor.props("Hey There"), "HeyThereProps")

        actorRef ! "Hey There"
        Thread.sleep(1000)

        actorRef ! "Hello There"
        Thread.sleep(1000)

        actorRef ! "Hey There"
        Thread.sleep(1000)
    }

    def testDeafActor(): Unit = {
        val actorRef: ActorRef = basicActorSystem.actorOf(Props[DeafActor](), "DeafActor")

        actorRef ! "Hello"
        Thread.sleep(1000)

        actorRef ! 12345
        Thread.sleep(1000)
    }

    def testCounterActor(): Unit = {
        val actorRef = basicActorSystem.actorOf(Counter.getProp(2), name = "BasicCounter")
        for (_ <- 0 to 11)
            actorRef ! "count"
    }

    def testDictionanry(): Unit = {
        val actorRef = basicActorSystem.actorOf(Props[DictionaryActor](), name = "DictionaryActor")
        actorRef ! DictionaryActor.Init("/src/resources/dictionary/technical_words.txt")
        actorRef ! DictionaryActor.IsWord("Hello")
        actorRef ! DictionaryActor.IsWord("ConcurrenCy")
        actorRef ! DictionaryActor.Init("/src/resources/dictionary/layman_words.txt")
        actorRef ! DictionaryActor.End()
        println

        actorRef ! DictionaryActor.Init("/src/resources/dictionary/layman_words.txt")
        actorRef ! DictionaryActor.IsWord("Concurrency")
        actorRef ! DictionaryActor.IsWord("apple")
    }

    def shutdown(): Unit = {
        basicActorSystem.terminate()
    }
}
