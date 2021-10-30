package students

trait Arts extends Subject {
    abstract override def getSubjectReview: String = s"This Subject is part of Arts Group. ${super.getSubjectReview}"
}
