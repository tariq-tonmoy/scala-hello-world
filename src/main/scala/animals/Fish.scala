package animals

class Fish(override val taste: FoodTaste.FoodTaste, override val calorie: Double) extends PetAnimalFood with WildAnimalFood {
    override def getFoodReview: String = s"Fish tastes ${taste}. ${super.getFoodReview}"
}
