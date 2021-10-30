package implicits.animalsounds

import animals._

class GoatWithSound(override val name: String) extends Goat(name) with AnimalSound {
    override def generateSound: String = s"${super.generateSound} 'Moo'"
}

