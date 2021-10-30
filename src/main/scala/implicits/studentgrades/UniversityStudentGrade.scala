package implicits.studentgrades

import students._

class UniversityStudentGrade[TSub <: Science](override val name: String) extends UniversityStudent[TSub](name) with StudentGrade[TSub] {
    override def grade(subject: TSub): String = s"${super.takes(subject)}\nUniversity Student takes ${subject.subjectName} and gets ${super.grade(subject)}\n"
}
