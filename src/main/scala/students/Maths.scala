package students

class Maths(override val credit: Double, override val subjectName: String, override val subjectType: SubjectType.SubjectType) extends Science with Commerce {
    override def getSubjectReview: String = s"Maths is ${subjectType}. ${super.getSubjectReview}"
}
