package Layout

final protected class UniformElement(
                                      ch: Char,
                                      override val height: Int,
                                      override val width: Int) extends Element {

    override def contents = {
        val row = ch.toString * (width)
        Array.fill(height)(row)
    }

    override def toString: String = s"Inside UniformElement. ${super.toString}"
}
