package animals

class Bear(override val name: String) extends Animal {
    override type FoodType = WildAnimalFood
}

