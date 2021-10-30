package abstracts

// Compilation Error: Parameter cannot have `lazy` val
trait LazyInitializer(/*lazy*/ val testVal: Int) {

    val abstractVal: Int
    val abstractFunc: () => Int

    val regularVal: Int = {
        println("\nInside regularVal")
        println(s"abstractVal = $abstractVal")
        println(s"abstractFunc = ${if (abstractFunc == null) "abstractFunc is NULL!!!!!" else abstractFunc()}")
        val _regularVal = abstractVal + (if (abstractFunc == null) 0 else abstractFunc())
        println(s"regularVal = $_regularVal")
        _regularVal
    }

    lazy val lazyVal: Int = {
        println("\nInside lazyVal")
        println(s"abstractVal = $abstractVal")
        println(s"abstractFunc = ${if (abstractFunc == null) "abstractFunc is NULL!!!!!" else abstractFunc()}")
        val _lazyVal = abstractVal + (if (abstractFunc == null) 0 else abstractFunc())
        // printing $lazyVal and not `_lazyVal` is going to end up in infinite recursion
        println(s"lazyVal = $_lazyVal")
        _lazyVal
    }

    require(testVal % 2 == 0 || {
        println("Inside require to initialize and test lazyVal")
        lazyVal >= 0
    } == true)
}

object LazyInitializer {
    private def getAbstractFunc(): Int = {
        val _abstractVal: Int = 29
        println(s"\nInside ObjectLazyInitializer -> getAbstractFunc -> $_abstractVal")
        _abstractVal
    }

    private def getAbstractVal: Int = {
        val _abstractVal: Int = 25
        println(s"\nInside ObjectLazyInitializer -> getAbstractVal -> $_abstractVal")
        _abstractVal
    }

    def testLazyInitializer = {
        println("\nSTART :: Lazy val initialized from Constructor")
        val lazyInitialized = new LazyInitializer(1) {
            override val abstractVal: Int = getAbstractVal
            override val abstractFunc: () => Int = getAbstractFunc
        }
        println("Initialized Lazy value:")
        println(lazyInitialized.lazyVal)
        println("END :: Lazy val initialized from Constructor")


        println("\nSTART :: Lazy val initialization delayed")
        val lazyDelayed = new LazyInitializer(0) {
            override val abstractVal: Int = getAbstractVal
            override val abstractFunc: () => Int = getAbstractFunc
        }
        println("Now lazy will be initialized:")
        println(lazyDelayed.lazyVal)
        println("END :: Lazy val initialization delayed")

    }
}