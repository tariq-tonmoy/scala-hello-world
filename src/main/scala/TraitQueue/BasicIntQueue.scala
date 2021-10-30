package TraitQueue

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue with IntComparer {
    private val buffer: ArrayBuffer[Int] = new ArrayBuffer[Int]

    override def put(x: Int): Unit = {
        println(s"Inside BasicIntQueue.put; Input: $x; put: $x")
        buffer += x
    }

    override def get(): Int = {
        if (buffer.length > 0) buffer.remove(0) else Int.MinValue
    }

    override def comparisonParamProvider: () => Int = provideComparionsMatrix

    private def provideComparionsMatrix(): Int = {
        buffer.sum / buffer.length
    }
}
