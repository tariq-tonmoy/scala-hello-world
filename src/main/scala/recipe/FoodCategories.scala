package recipe

trait FoodCategories extends SimpleFoods with ExoticFoods {
    object Vegitable extends FoodCategory {
        override val category: String = "Vegetable"
        override val foods: List[Food] = List(Capsicum)
    }

    object Fruit extends FoodCategory {
        override val category: String = "Fruit"
        override val foods: List[Food] = List(Apple, Orange, Pear)
    }

    object Dairy extends FoodCategory {
        override val category: String = "Dairy"
        override val foods: List[Food] = List(Butter, SourCream)
    }

    object JunkFood extends FoodCategory {
        override val category: String = "Junk Food"
        override val foods: List[Food] = List(Ramen, FrozenFood)
    }

    def allFoodCategories = List(Vegitable, Fruit, Dairy, JunkFood)
}
