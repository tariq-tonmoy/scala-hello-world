package recipe

abstract class FoodCategory {
    val category: String
    val foods: List[Food]

    override def toString: String = s"$category: ${foods.mkString(",")}"
}
