import Layout._

object Chapter10 {

    def printElementProperties(element: Element) = {
        Console println s"${element.toString}"
        Console println element.getContentsAsString
    }

    @main def chapterTenTests = {
        val arrayElement: Element = Element.createElement(Array("Hello, ", "World!"))

        printElementProperties(arrayElement)

        val singleStringForLinearElement: Element = Element.createElement("Single String For LinearElement")

        printElementProperties(singleStringForLinearElement)

        val singleStringWithNamedArgumentForLinearElement: Element = Element.createElement(s = "Single String With Named Argument For LinearElement")

        printElementProperties(singleStringWithNamedArgumentForLinearElement)

        val singleStringWithNamedArgumentForConcatenatedElement: Element = Element.createElement(stringContents = "Single String With Named Argument For ConcatenatedElement")

        printElementProperties(singleStringWithNamedArgumentForConcatenatedElement)

        val uniformElement: Element = Element.createElement('x', 4, 5)

        printElementProperties(uniformElement)

        val concatenatedElements = arrayElement above singleStringWithNamedArgumentForLinearElement
        printElementProperties(concatenatedElements)

        val concatenatedElement: Element = Element.createElement("1. Hello, World!", "10. Hello, World!")
        printElementProperties(concatenatedElement)
    }
}
