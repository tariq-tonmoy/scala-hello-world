package abstracts

trait Abstract {
    type T

    def transform(x: T): T

    val initial: T
    var current: T

    //  Compilation Error: Incorrect use of _
    //  val implementedInitial: T = _

    var implementedCurrent: T = _

    val valCannotOverrideAsDef: T

    def defCanOverrideAsVal: T
}
