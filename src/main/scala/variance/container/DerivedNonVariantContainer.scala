package variance.container

import variance.hierarchy._

class DerivedNonVariantContainer[M, N](m: M, n: N) extends BaseContainer[M, N](m, n) {

}

object DerivedNonVariantContainer {
    def testDerivedNonVariantContainer = {
        val derivedNonVariantContainerST: DerivedNonVariantContainer[S, T] = new DerivedNonVariantContainer[S, T](new U, new V)
        //        Compilation Error: DerivedNonVariantContainer[R, U] = new DerivedNonVariantContainer[S, T] not possible
        //        since DerivedNonVariantContainer is non variant

        //        val derivedNonVariantContainerST_RU: DerivedNonVariantContainer[R, U] = new DerivedNonVariantContainer[S, T](new U, new V)
    }
}