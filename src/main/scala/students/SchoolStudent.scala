package students

class SchoolStudent[TSub <: Arts](override val name: String) extends Student[TSub] {
    override def takes(subject: TSub): String = s"Hola! ${super.takes(subject)}"
}
