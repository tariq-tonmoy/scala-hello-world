package variance

import variance.hierarchy._

class SuperTypeLowerBoundTesterService[X >: T] {
    def testTypeBound(x: X): Unit = {}
}

object SuperTypeLowerBoundTesterService {
    object SuperTypeLowerBoundTesterServiceExtendsP extends SuperTypeLowerBoundTesterService[P]

    object SuperTypeLowerBoundTesterServiceExtendsQ extends SuperTypeLowerBoundTesterService[Q]

    object SuperTypeLowerBoundTesterServiceExtendsR extends SuperTypeLowerBoundTesterService[R]

    object SuperTypeLowerBoundTesterServiceExtendsS extends SuperTypeLowerBoundTesterService[S]

    object SuperTypeLowerBoundTesterServiceExtendsT extends SuperTypeLowerBoundTesterService[T]

    SuperTypeLowerBoundTesterServiceExtendsT.testTypeBound(new T)

    SuperTypeLowerBoundTesterServiceExtendsT.testTypeBound(new U)

    //  Compilation Error: Parameter passing is always  subtype -> supertype
    //  SuperTypeLowerBoundTesterServiceExtendsT.testTypeBound(new S)


    //    Compilation Error: Extensions must be from super types of T
    //
    //    object SuperTypeLowerBoundTesterServiceExtendsU extends SuperTypeLowerBoundTesterService[U]
    //
    //    object SuperTypeLowerBoundTesterServiceExtendsV extends SuperTypeLowerBoundTesterService[V]
    //
    //    object SuperTypeLowerBoundTesterServiceExtendsW extends SuperTypeLowerBoundTesterService[W]
}