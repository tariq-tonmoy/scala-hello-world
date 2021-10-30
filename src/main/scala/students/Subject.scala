package students

trait Subject {
    val subjectType: SubjectType.SubjectType
    val credit: Double
    val subjectName: String

    def getSubjectReview: String = s"$subjectName has $credit credits"
}
