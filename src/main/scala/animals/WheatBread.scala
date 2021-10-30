package animals

final class WheatBread(override val taste: FoodTaste.FoodTaste, override val calorie: Double) extends PetAnimalFood with FarmAnimalFood {
    override def getFoodReview: String = s"Wheat Bread tastes ${taste}. ${super.getFoodReview}"
}
