package recipe

trait RecipeBrowser {
    this: Database =>
    def findAllFood = foods

    def findAllRecepies = recipies

    def findAllCategories = allFoodCategories

    def findRecipesByFood(food: Food): List[FoodRecipe] = recipies.filter(_.foods.contains(food))

    def findFood(name: String): Option[Food] = foods.find(_.name == name)

    def findCategoryByFood(food: Food): List[FoodCategory] = allFoodCategories.filter(_.foods.contains(food))
}
