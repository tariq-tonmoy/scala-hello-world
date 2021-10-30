import abstracts.*
import animals._

object Chapter20 {

    def testAbstractVar = {
        val abstractTimer = AbstractTimeVar.testAbstractTimeVar
        println(s"abstractTimer ${abstractTimer.testVal}-> ${abstractTimer.hour}::${abstractTimer.minute}")

        val traitTimer = TraitTimerVar.testTraitTimerVar
        println(s"traitTimer ${traitTimer.testVal}-> ${traitTimer.hour}::${traitTimer.minute}")

        traitTimer.hour = 21
        traitTimer.minute = 54
        println(s"mutated traitTimer ${traitTimer.testVal}-> ${traitTimer.hour}::${traitTimer.minute}")

        val timer: ConcreteTimer = new ConcreteTimer
        println("Printing Month ........")
        println("Old Month:" + timer.month)
        timer.month_=(91)
        println("New Month " + timer.month)

        println
        println("Printing Year ........")
        println("Year def: " + timer.yearDef)
        println("Year: " + timer.year)
    }

    def testAnimalFoods = {
        val tastyFish = new Fish(FoodTaste.TASTY, 3.14)
        val moderateFish = new Fish(FoodTaste.MODERATE, 15.09)

        val tastyGrass = new Grass(FoodTaste.TASTY, 0.89)
        val disgustingGrass = new Grass(FoodTaste.DISGUSTING, 0.01)

        val disgustingBread = new WheatBread(FoodTaste.DISGUSTING, 1.09)
        val moderateBread = new WheatBread(FoodTaste.MODERATE, 2.83)

        val molly = new Cow("Molly")
        val dolly = new Cat("Dolly")
        val beary = new Bear("Beary")

        molly.eats(tastyGrass)
        molly.eats(moderateBread)

        dolly.eats(tastyFish)
        dolly.eats(disgustingBread)

        beary.eats(moderateFish)
        beary.eats(disgustingGrass)

        val farmAnimals = List[Animal {type FoodType = FarmAnimalFood}](new Cow("Cow 1"), new Goat("Goat 1"))

        val petAnimals = List[Animal {type FoodType = PetAnimalFood}](new Dog("Doggo"), new Cat("Cat 1"))
    }

    @main def chapterTwentyTests = {
        testAbstractVar
        Initialization.testInitialization
        LazyInitializer.testLazyInitializer
        testAnimalFoods
    }
}
