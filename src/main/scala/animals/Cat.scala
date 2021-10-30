package animals

class Cat(override val name: String) extends Animal {
    override type FoodType = PetAnimalFood
}
