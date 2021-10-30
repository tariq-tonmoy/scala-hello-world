package object bobsdelights {

    def showFruits(fruits: Fruits) = {
        import fruits._

        println(s"${name}s are $color")
    }
}
