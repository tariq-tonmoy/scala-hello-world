package animals

trait PetAnimalFood extends Food {
    abstract override def getFoodReview: String = s"Pet Animals Eat this. ${super.getFoodReview}"
}
