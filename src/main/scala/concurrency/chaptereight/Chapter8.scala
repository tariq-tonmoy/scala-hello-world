package concurrency.chaptereight

import concurrency.chaptereight.actormodel.BasicActorSystem

object Chapter8 {
    @main def chapterEightTests(): Unit = {
        //        BasicActorSystem.testBasicActorSystem()
        //        BasicActorSystem.testDeafActor()
        //        BasicActorSystem.testCounterActor()
        BasicActorSystem.testDictionanry()

        BasicActorSystem.shutdown()
    }
}
