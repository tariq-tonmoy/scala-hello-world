package implicits.helpers

import students._


object StudentProvider {
    def getScienceStudent: Student[Science] = {
        new UniversityStudent("A University Student")
    }

    def getCommerceStudent: Student[Commerce] = {
        new CollegeStudent("A College Student")

    }

    def getArtsStudent: Student[Arts] = {
        new SchoolStudent("A School Student")
    }

    def getBoringMaths = {
        new Maths(3.14, "Integral Calculas", SubjectType.BORING)
    }

    def getModerateEconomics = {
        new Economics(2.10, "Introduction to Economics", SubjectType.MODERATE)
    }

    def getInterestingEnglish = {
        new English(4.00, "Intermediate English Grammar", SubjectType.INTERESTING)
    }

    class StudentActionHelper[TSub <: Subject](student: Student[TSub]) {
        def -->(subject: TSub): String = {
            student.takes(subject)
        }
    }

    implicit def studentToActionConverter[TSub <: Subject](student: Student[TSub]): StudentActionHelper[TSub] = {
        new StudentActionHelper(student)
    }
}
