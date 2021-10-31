package recipe

abstract class FoodRecipe(val name: String, val instruction: String, val foods: List[Food]) {
    override def toString: String = s"$name: ${foods.mkString(", ")}\nInstruction: $instruction"
}
