package implicits.helpers

import animals._

object AnimalsProvider {
    def getCow(name: String): Animal = {
        new Cow(name)
    }

    def getGoat(name: String): Animal = {
        new Goat(name)
    }

    def getBear(name: String): Animal = {
        new Bear(name)
    }

    def getGrass: Grass = {
        new Grass(FoodTaste.TASTY, 9.01)
    }

    def getFish: Fish = {
        new Fish(FoodTaste.DISGUSTING, 0.001)
    }

    trait AnimalActionHelper extends Animal {
        def x(food: Food): Unit
    }


    class CowActionHelper(override val name: String) extends Cow(name) with AnimalActionHelper {
        override def x(food: Food): Unit = {
            food match {
                case x: FoodType => eats(food.asInstanceOf[FoodType])
                case _ => null
            }
        }
    }

    class GoatActionHelper(override val name: String) extends Goat(name) with AnimalActionHelper {
        override def x(food: Food): Unit = {
            food match {
                case x: FoodType => eats(food.asInstanceOf[FoodType])
                case _ => null
            }
        }
    }

    class CatActionHelper(override val name: String) extends Cat(name) with AnimalActionHelper {
        override def x(food: Food): Unit = {
            food match {
                case x: FoodType => eats(food.asInstanceOf[FoodType])
                case _ => null
            }
        }
    }

    class DogActionHelper(override val name: String) extends Dog(name) with AnimalActionHelper {
        override def x(food: Food): Unit = {
            food match {
                case x: FoodType => eats(food.asInstanceOf[FoodType])
                case _ => null
            }
        }
    }

    class BearActionHelper(override val name: String) extends Bear(name) with AnimalActionHelper {
        override def x(food: Food): Unit = {
            food match {
                case x: FoodType => eats(food.asInstanceOf[FoodType])
                case _ => null
            }
        }
    }

    implicit def convertAnimalToActionHelper(animal: Animal): AnimalActionHelper = {

        animal match {
            case _: Cow => new CowActionHelper(animal.name)
            case _: Goat => new GoatActionHelper(animal.name)
            case _: Dog => new DogActionHelper(animal.name)
            case _: Cat => new CatActionHelper(animal.name)
            case _: Bear => new BearActionHelper(animal.name)
            case _ => ???
        }
    }
}
