package students

class CollegeStudent[TSub <: Commerce](override val name: String) extends Student[TSub] {

    override def takes(subject: TSub): String = s"Hi! ${super.takes(subject)}"
}
