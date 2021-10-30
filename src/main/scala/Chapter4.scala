object Chapter4 {
    @main def chapterFourTestsWithAnnotatedMain(hello: String, scala3: String) = println(hello + scala3)

    def main(args: Array[String]) = println(args(0) + " " + args(1))
}


object Chapter4App extends App {
    //https://docs.scala-lang.org/scala3/reference/changed-features/main-functions.html
    Console println "No Arguments supported in Scala3!!!"

    Console println (args)
}
