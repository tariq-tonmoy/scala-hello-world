package Layout

import Layout.Element.createElement

abstract class Element {
    def contents: Array[String]

    // cannot override parameterless and parenthesis-only methods
    // def contents(): Array[String]

    def height: Int = {
        contents.length
    }

    def width: Int = contents.map(content => content.length).max

    override def toString: String = s"Height: $height. Width: $width"

    def getContentsAsString = {
        contents.mkString("\n")
    }

    def above(that: Element): Element = {
        val firstContents = this widen that.width
        val secondContents = that widen this.width

        assert(firstContents.width == secondContents.width)
        createElement(firstContents.contents ++ secondContents.contents)
    }

    def beside(that: Element): Element = {
        createElement(
            for {
                (line1, line2) <- this.contents zip that.contents
            }
            yield {
                line1 + line2
            }
        )
    }

    def widen(w: Int): Element = {
        val newElement = if (w <= this.width) {
            this
        }
        else {
            val leftContents: Element = createElement('_', this.height, (w - this.width) / 2)
            val rightContents: Element = createElement('_', this.height, w - (this.width + leftContents.width))

            leftContents beside this beside rightContents
        }

        newElement.ensuring(x => w <= x.width)
    }

    def heighten(h: Int): Element = {
        val newElement = if (h <= this.height) {
            this
        }
        else {
            val topContents: Element = createElement(' ', (h - this.height) / 2, this.width)
            val bottomContents: Element = createElement(' ', h - (this.height + topContents.height), this.width)

            topContents above this above bottomContents
        }

        newElement
    }
}

object Element {
    private class ConcatenatedElement(stringContents: String*) extends Element {
        def contents: Array[String] = stringContents.toArray[String]

        override def toString(): String = s"Inside ConcatenatedElement. ${super.toString()}"
    }

    def createElement(contents: Array[String]): Element = {
        new ArrayElement(contents)
    }

    def createElement(s: String): Element = {
        new LinearElement(s)
    }

    def createElement(ch: Char, height: Int, width: Int): Element = {
        new UniformElement(ch, height, width)
    }

    def createElement(stringContents: String*): Element = {
        new ConcatenatedElement(stringContents: _*)
    }
}
