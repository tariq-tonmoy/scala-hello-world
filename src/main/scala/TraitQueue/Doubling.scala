package TraitQueue

trait Doubling extends IntQueue {

    abstract override def put(x: Int): Unit = {
        println(s"Inside Doubling.put; Input: $x; put: ${x * 2}")
        super.put(x * 2)
    }

    protected def alterInput(x: => Int): Int = {
        println("inside Doubling.alterInput")
        println(s"Inside Doubling.alterInput; Input: $x; output: ${x - 1}")
        x - 1
    }
}
