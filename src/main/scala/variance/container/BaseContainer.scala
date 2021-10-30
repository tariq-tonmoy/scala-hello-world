package variance.container

import variance.hierarchy._

class BaseContainer[+X, -Y](private val x: X, y: Y) {

    override def toString: String = s"BaseContainer($x, $y) :>"

    def getContainerY2X(y: Y): X = {
        println(s"getContainerY2X: $y")
        x
    }

    def getContainer[A >: X, B <: Y](a: A, b: B): BaseContainer[A, B] = {
        println(s"getContainer: a: $a, b: $b")
        new BaseContainer(a, b)
    }
}

object BaseContainer {
    def testBaseContainer = {
        // T >: U and U >: U1. All are covariant in constructor params
        val baseTT1: BaseContainer[T, T1] = new BaseContainer[T, T1](new U, new U1)
        println(s"baseTT1 from UU1 $baseTT1")

        val baseQR_PS: BaseContainer[P, S] = new BaseContainer[Q, R](new U, new U1)
        println(s"baseQR_PS from UU1 $baseQR_PS")

        val basePP_PQ: BaseContainer[P, Q] = new BaseContainer[P, P](new U, new U1)
        println(s"basePP_PQ from UU1 $basePP_PQ")

        val baseUU1_TV1: BaseContainer[T, V1] = new BaseContainer[U, U1](new U, new U1)
        println(s"baseUU1_TV1 from UU1 $baseUU1_TV1")

        val baseTT1_SU1: BaseContainer[S, U1] = new BaseContainer[T, T1](new U, new U1)
        println(s"baseTT1_SU1 from UU1 $baseTT1_SU1")

        val baseTS_ST1: BaseContainer[S, T1] = new BaseContainer[T, S](new U, new U1)
        println(s"baseTS_ST1 from UU1 $baseTS_ST1")

        val baseTT1_TU1: BaseContainer[T, U1] = baseTT1.getContainer(new W, new U1)

        val baseTT1_SV1: BaseContainer[S, V1] = baseTT1.getContainer(new S, new V1)

        val baseST1_SW1: BaseContainer[S, W1] = baseTS_ST1.getContainer(new T1, new W1)

        val baseST1_PW1: BaseContainer[P, W1] = baseTS_ST1.getContainer(new T1, new W1)


        // Liskov's Substitution Principle

        val containerU1T = new ContainerU1T
        val containerT1U = new ContainerT1U

        // Base Class
        val containerU1T_TU1: U1 = containerU1T.getContainerY2X(new T)
        val containerU1T_UT1: T1 = containerU1T.getContainerY2X(new U)
        val containerU1T_WP: P = containerU1T.getContainerY2X(new W)

        // Derived Class
        val containerT1U_TU1: U1 = containerT1U.getContainerY2X(new T)
        val containerT1U_UT1: T1 = containerT1U.getContainerY2X(new U)
        val containerT1U_WP: P = containerT1U.getContainerY2X(new W)
    }
}