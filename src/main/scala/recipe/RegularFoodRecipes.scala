package recipe


trait RegularFoodRecipes extends SimpleFoods with ExoticFoods {

    object FruitSalad extends FoodRecipe("Fruit Salad", "Put in a bowl. Stir", List(Apple, Orange, Pear))

    object VegetableSalad extends FoodRecipe("Vegetable Salad", "Put on an frypan. Stir Fry", List(Butter, Capsicum, SourCream))
}
