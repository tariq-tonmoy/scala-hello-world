package students

class English(override val credit: Double, override val subjectName: String, override val subjectType: SubjectType.SubjectType) extends Science with Arts {
    override def getSubjectReview: String = s"English is ${subjectType}. ${super.getSubjectReview}"
}
