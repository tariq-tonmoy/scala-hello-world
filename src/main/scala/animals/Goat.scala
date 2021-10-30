package animals

class Goat(override val name: String) extends Animal {
    override type FoodType = FarmAnimalFood
}
