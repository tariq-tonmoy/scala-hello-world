package students

trait Commerce extends Subject {
    abstract override def getSubjectReview: String = s"This Subject is part of Commerce Group. ${super.getSubjectReview}"
}
