package recipe

trait RegularRecipeDb extends Database with RegularFoodRecipes {

    override def recipies: List[FoodRecipe] = List(FruitSalad, VegetableSalad)

    override def foods: List[Food] = List(Apple, Orange, Pear, Butter, Capsicum, SourCream)
}
