package implicits.animalsounds

import animals._


trait AnimalSound extends Animal {
    def generateSound: String = s"$name says:"
}

object AnimalSound {
    implicit def generateAnimalWithSound(animal: Animal): AnimalSound = {
        animal match {
            case _: Cow => new CowWithSound(animal.name)
            case _: Goat => new GoatWithSound(animal.name)
            case _ => ???
        }
    }
}
