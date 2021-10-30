package variance

import variance.hierarchy._

// Compilation Error
// class TightBoundTesterService[X >: R <: T]
// class TightBoundTesterService[X <: R >:T]

class TightBoundTesterService[X >: T <: R] {

}

object TightBoundTesterService {
    //    Compilation Error: extensions must be Subtype of R
    //    object TightBoundTesterServiceExtendsP extends TightBoundTesterService[P]
    //
    //    object TightBoundTesterServiceExtendsQ extends TightBoundTesterService[Q]

    object TightBoundTesterServiceExtendsR extends TightBoundTesterService[R]

    object TightBoundTesterServiceExtendsS extends TightBoundTesterService[S]

    object TightBoundTesterServiceExtendsT extends TightBoundTesterService[T]

    //    Compilation Error: Extensions must be SuperType of T
    //    object TightBoundTesterServiceExtendsU extends TightBoundTesterService[U]
    //
    //    object TightBoundTesterServiceExtendsV extends TightBoundTesterService[V]
    //
    //    object TightBoundTesterServiceExtendsW extends TightBoundTesterService[W]
}

