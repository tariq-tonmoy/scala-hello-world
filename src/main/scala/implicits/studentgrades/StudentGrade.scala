package implicits.studentgrades

import students._

trait StudentGrade[TSub <: Subject] {
    def grade(subject: TSub): String = {
        if (subject.credit >= 4.0d) "A+" else if (subject.credit > 3.5d && subject.credit < 4.0d) "B" else "F"
    }

    def test(science: Science) = {
        println(s"Hello ${science.subjectName}")
    }
}

object StudentGrade {
    implicit def extendStudentWithGrade[TSub <: Subject, TCommerce <: Commerce, TScience <: Science, TArts <: Arts](student: Student[TSub]): StudentGrade[TSub] = {
        student match {
            case _: CollegeStudent[TCommerce] => new CollegeStudentGrade[TCommerce](student.name)
            case _: UniversityStudent[TScience] => new UniversityStudentGrade[TScience](student.name)
            case _: SchoolStudent[TArts] => new SchoolStudentGrade[TArts](student.name)
            case _ => ???
        }
    }
}


