package recipe

trait RecipeMixer {
    this: Database & ComplexMixer =>

    def browseAllFood = foods ::: browseMixerFood

    def browseAllRecipies = recipies ::: browseMixerRecipes

    def browseAllCategories = foodCategories ::: browseMixerCategories

    private def browseMixerFood: List[Food] = if (recipeMixer == null) Nil else recipeMixer.browseAllFood

    private def browseMixerRecipes: List[FoodRecipe] = if (recipeMixer == null) Nil else recipeMixer.browseAllRecipies

    private def browseMixerCategories: List[FoodCategory] = if (recipeMixer == null) Nil else recipeMixer.browseAllCategories
}
