package recipe

trait StudentRecipeDb extends Database with StudentFoodRecipes {

    override def recipies: List[FoodRecipe] = List(FrozenQuickFood, RamenRecipe)

    override def foods: List[Food] = List(FrozenFood, SourCream, Ramen, Butter)
}
