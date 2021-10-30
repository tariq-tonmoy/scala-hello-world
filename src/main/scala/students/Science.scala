package students

trait Science extends Subject {
    abstract override def getSubjectReview: String = s"This Subject is part of Science Group. ${super.getSubjectReview}"
}
