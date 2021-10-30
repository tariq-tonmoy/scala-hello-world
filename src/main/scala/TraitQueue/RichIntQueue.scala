package TraitQueue

class RichIntQueue extends BasicIntQueue with Doubling with Filtering {
    override def put(x: Int): Unit = {
        val input = alterInput(testByNameParameter(x))
        println(s"Inside RichIntQueue.put; Input: $x; put: ${input}")
        super.put(input)
    }

    private def testByNameParameter(x: Int): Int = {
        println(s"Inside ByName parameter: $x")
        x
    }

    //Since both Doubling and Filtering have alterInput Method, RichIntQueue must override the method
    override def alterInput(x: => Int): Int = super.alterInput(x)

    // cannot do it
    // def testAbstractMethod(x: Int): Unit
}

object RichIntQueue {
    def apply(numbers: Int*): RichIntQueue = {
        val richIntQueue: RichIntQueue = new RichIntQueue
        for (num <- numbers) {
            richIntQueue.put(num)
        }

        richIntQueue
    }
}