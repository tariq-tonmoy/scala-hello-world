package Layout

//override in 'contents' -> optional
final protected class ArrayElement(override val contents: Array[String]) extends Element {
    override def height: Int = {
        println(s"Height in sub class: ${contents.length}")
        super.height
    }

    override def toString(): String = s"Inside ArrayElement. ${super.toString()}"

    // cannot work without override
    //     def width: Int = {
    //        println(s"Width in sub class: ${contents.length + 1}")
    //        contents.length + 1
    //    }
}
