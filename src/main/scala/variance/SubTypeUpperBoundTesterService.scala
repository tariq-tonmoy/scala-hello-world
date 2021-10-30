package variance

import variance.hierarchy._

class SubTypeUpperBoundTesterService[X <: T] {
    def testTypeBound(x: X): Unit = {}
}

object SubTypeUpperBoundTesterService {
    //    Compilation Error: extensions must be from subtypes of T
    //    object SubTypeUpperBoundTesterServiceExtendsP extends SubTypeUpperBoundTesterService[P]
    //
    //    object SubTypeUpperBoundTesterServiceExtendsQ extends SubTypeUpperBoundTesterService[Q]
    //
    //    object SubTypeUpperBoundTesterServiceExtendsR extends SubTypeUpperBoundTesterService[R]
    //
    //    object SubTypeUpperBoundTesterServiceExtendsS extends SubTypeUpperBoundTesterService[S]

    object SubTypeUpperBoundTesterServiceExtendsT extends SubTypeUpperBoundTesterService[T]

    object SubTypeUpperBoundTesterServiceExtendsU extends SubTypeUpperBoundTesterService[U]

    object SubTypeUpperBoundTesterServiceExtendsV extends SubTypeUpperBoundTesterService[V]

    object SubTypeUpperBoundTesterServiceExtendsW extends SubTypeUpperBoundTesterService[W]

    SubTypeUpperBoundTesterServiceExtendsT.testTypeBound(new T)

    SubTypeUpperBoundTesterServiceExtendsT.testTypeBound(new U)

    //  Compilation Error
    //  SubTypeUpperBoundTesterServiceExtendsT.testTypeBound(new S)

}
