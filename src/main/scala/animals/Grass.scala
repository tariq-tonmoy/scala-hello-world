package animals

final class Grass(override val taste: FoodTaste.FoodTaste, override val calorie: Double) extends FarmAnimalFood with WildAnimalFood {

    override def getFoodReview: String = s"Grass tastes ${taste}. ${super.getFoodReview}"
}
