package animals

trait WildAnimalFood extends Food {
    abstract override def getFoodReview: String = s"Wild Animals Eat this and ${super.getFoodReview}"
}
