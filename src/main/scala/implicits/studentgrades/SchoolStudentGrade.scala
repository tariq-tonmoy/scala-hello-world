package implicits.studentgrades

import students._

class SchoolStudentGrade[TSub <: Arts](override val name: String) extends SchoolStudent[TSub](name) with StudentGrade[TSub] {
    override def grade(subject: TSub): String = s"School Student takes ${subject.subjectName} and gets ${super.grade(subject)}"
}
