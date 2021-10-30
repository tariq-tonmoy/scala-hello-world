package TraitQueue

trait Filtering extends IntQueue {
    abstract override def put(x: Int): Unit = {
        println(s"Inside Filtering.put; Input: $x; put: ${x}")
        if (x > 0) super.put(x)
    }

    protected def alterInput(x: => Int): Int = {
        println(s"Inside Filtering.alterInput")
        println(s"Inside Filtering.alterInput; Input: $x; output: ${x - 2}")
        x - 2
    }
}
