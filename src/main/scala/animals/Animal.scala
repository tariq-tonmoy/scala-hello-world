package animals

trait Animal {
    type FoodType <: Food
    val name: String

    def eats(food: FoodType): Unit = {
        println(s"${name} is eating. ${food.getFoodReview}")
    }
}
