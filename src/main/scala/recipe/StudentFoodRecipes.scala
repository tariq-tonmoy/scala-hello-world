package recipe

trait StudentFoodRecipes extends SimpleFoods with ExoticFoods {

    object FrozenQuickFood extends FoodRecipe("Frozen Quick Food", "Put in an Oven. Heat", List(FrozenFood, SourCream))

    object RamenRecipe extends FoodRecipe("Ramen Noodles", "Put in Boiling Water for 2 minutes", List(Ramen, Butter))
}
