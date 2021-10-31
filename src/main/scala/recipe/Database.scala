package recipe

trait Database extends FoodCategories {

    def recipies: List[FoodRecipe]

    def foods: List[Food]

    def foodCategories = allFoodCategories
}
