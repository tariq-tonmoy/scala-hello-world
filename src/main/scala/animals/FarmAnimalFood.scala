package animals

trait FarmAnimalFood extends Food {
    abstract override def getFoodReview: String = s"Farm Animals Eat this. ${super.getFoodReview}"
}
