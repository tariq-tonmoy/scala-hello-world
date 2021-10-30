package implicits.animalsounds

import animals._

class CowWithSound(override val name: String) extends Cow(name) with AnimalSound {
    override def generateSound: String = s"${super.generateSound} 'Moo'"
}

