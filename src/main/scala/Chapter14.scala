import Layout._

object Chapter14 {

    def testLayoutElementsAssertion = {
        val elementA = Element.createElement("Hello,", "World!")
        val elementB = Element.createElement("********", "*********", "********")

        val elementC = elementA above elementB

        println(elementC.getContentsAsString)
    }

    @main def chapterFourteenTests = {
        testLayoutElementsAssertion
    }
}
