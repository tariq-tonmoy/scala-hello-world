package animals

abstract class Food {
    val taste: FoodTaste.FoodTaste
    def calorie: Double

    def getFoodReview: String = s"This food has ${calorie} calories"
}
