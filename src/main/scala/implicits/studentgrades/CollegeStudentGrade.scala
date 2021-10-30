package implicits.studentgrades

import students._

class CollegeStudentGrade[TSub <: Commerce](override val name: String) extends CollegeStudent[TSub](name) with StudentGrade[TSub] {

    override def grade(subject: TSub): String = s"College Student takes ${subject.subjectName} and gets ${super.grade(subject)}"
}
