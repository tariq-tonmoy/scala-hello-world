package recipe

abstract class ComplexRecipeBrowser extends RecipeBrowser {
    this: Database & RecipeMixer & ComplexMixer =>

    override def findAllFood = this.browseAllFood

    override def findAllRecepies = this.browseAllRecipies

    override def findAllCategories = this.browseAllCategories

    override def findRecipesByFood(food: Food): List[FoodRecipe] = findAllRecepies.filter(_.foods.contains(food))

    override def findFood(name: String): Option[Food] = findAllFood.find(_.name == name)

    override def findCategoryByFood(food: Food): List[FoodCategory] = findAllCategories.filter(_.foods.contains(food))
}
