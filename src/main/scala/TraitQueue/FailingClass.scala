package TraitQueue


class FailingClass extends FailingAbstractClass /*with AnotherTrait*/ {
    /*abstract*/ override def abstractMethod(x: Int): String = {
        x.toString
    }
}
