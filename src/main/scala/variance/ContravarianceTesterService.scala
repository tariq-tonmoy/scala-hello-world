package variance

import hierarchy._

class ContravarianceTesterService[-X] {

}

object ContravarianceTesterService {
    object ContravarianceTesterServiceExtendsT extends ContravarianceTesterService[T]

    //    Compilation Error: [-X] = T, Contravariance relationship dictates A[subtype] = A[supertype]
    //    val contravarianceTesterServiceP: ContravarianceTesterService[P] = ContravarianceTesterServiceExtendsT
    //    val contravarianceTesterServiceQ: ContravarianceTesterService[Q] = ContravarianceTesterServiceExtendsT
    //    val contravarianceTesterServiceR: ContravarianceTesterService[R] = ContravarianceTesterServiceExtendsT
    //    val contravarianceTesterServiceS: ContravarianceTesterService[S] = ContravarianceTesterServiceExtendsT

    val contravarianceTesterServiceT: ContravarianceTesterService[T] = ContravarianceTesterServiceExtendsT
    val contravarianceTesterServiceU: ContravarianceTesterService[U] = ContravarianceTesterServiceExtendsT
    val contravarianceTesterServiceV: ContravarianceTesterService[V] = ContravarianceTesterServiceExtendsT
    val contravarianceTesterServiceW: ContravarianceTesterService[W] = ContravarianceTesterServiceExtendsT
}
