package students

trait Student[TSub <: Subject] {
    val name: String

    def takes(subject: TSub): String = {
        s"${name} is taking ${subject.subjectName}. ${subject.getSubjectReview}"
    }
}
