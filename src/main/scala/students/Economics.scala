package students

class Economics(override val credit: Double, override val subjectName: String, override val subjectType: SubjectType.SubjectType) extends Commerce with Arts {
    override def getSubjectReview: String = s"Economics is ${subjectType}. ${super.getSubjectReview}"
}
