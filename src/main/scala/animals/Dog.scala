package animals

class Dog(override val name: String) extends Animal {
    override type FoodType = PetAnimalFood
}
