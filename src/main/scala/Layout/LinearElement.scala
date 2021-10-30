package Layout

//pass parameter to super class
//class LinearElement(s: String) extends ArrayElement(Array(s))
final protected class LinearElement(s: String) extends Element {
    override def contents = Array(s)

    override def height: Int = s.length

    override def width: Int = 1

    override def toString(): String = s"Inside LinearElement. ${super.toString()}"
}
