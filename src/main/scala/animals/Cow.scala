package animals

class Cow(override val name: String) extends Animal {
    override type FoodType = FarmAnimalFood
}
