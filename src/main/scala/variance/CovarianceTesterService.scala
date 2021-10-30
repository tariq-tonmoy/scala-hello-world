package variance

import hierarchy._

class CovarianceTesterService[+X] {

}

object CovarianceTesterService {
    object CovarianceTesterServiceExtendsT extends CovarianceTesterService[T]

    val covarianceTesterServiceP: CovarianceTesterService[P] = CovarianceTesterServiceExtendsT
    val covarianceTesterServiceQ: CovarianceTesterService[Q] = CovarianceTesterServiceExtendsT
    val covarianceTesterServiceR: CovarianceTesterService[R] = CovarianceTesterServiceExtendsT
    val covarianceTesterServiceS: CovarianceTesterService[S] = CovarianceTesterServiceExtendsT
    val covarianceTesterServiceT: CovarianceTesterService[T] = CovarianceTesterServiceExtendsT

    //    Compilation Error: [+X] = T, Covariance relationship dictates A[supertype] = A[subtype]
    //    val covarianceTesterServiceU: CovarianceTesterService[U] = CovarianceTesterServiceExtendsT
    //    val covarianceTesterServiceV: CovarianceTesterService[V] = CovarianceTesterServiceExtendsT
    //    val covarianceTesterServiceW: CovarianceTesterService[W] = CovarianceTesterServiceExtendsT
}
