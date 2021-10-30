package students

class UniversityStudent[TSub <: Science](override val name: String) extends Student[TSub] {
    override def takes(subject: TSub): String = s"hello! ${super.takes(subject)}"
}
