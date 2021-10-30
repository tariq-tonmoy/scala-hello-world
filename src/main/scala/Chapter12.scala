import TraitQueue._
import TraitTree._

object Chapter12 {

    def testTraitQueue = {
        val queue: IntQueue = RichIntQueue(10)

        println(queue.get())


        val queue1: BasicIntQueue = RichIntQueue(51)

        val queue2: BasicIntQueue = RichIntQueue(10, 90)

        val queue3: BasicIntQueue = RichIntQueue(49)

        val queue4: BasicIntQueue = RichIntQueue(10, 90)

        val queue5: BasicIntQueue = RichIntQueue(50)

        val queue6: BasicIntQueue = RichIntQueue(10, 90)


        if (queue1 > queue2) {
            println("queue1 is greater than queue2")
        }

        if (queue3 < queue4) {
            println("queue4 is greater than queue3")
        }

        if ((queue5 > queue6) == false && (queue6 > queue5) == false) {
            println("queue5 and queue6 are equal")
        }

    }

    def testTraitTree = {
        println(new ClassLevel_1)
    }

    @main def chapterTwelveTests = {
        testTraitQueue
        testTraitTree
    }
}
