package variance

import hierarchy._

abstract class VarianceTypeCheckTesterService[+X, -Y] {

    def foo(y: Y): X

    // foofoo: [SuperType] => [SubType]
    // +X takes all subtypes. so we inverted A to take X's supertypes
    // -Y takes all supertypes. so we inverted B to take Y's subtypes
    def foofoo[A >: X, B <: Y](a: A): B

    def foobar[A >: X](a: A): X

    //    Compilation error: function parameters should be contravariance and return should be covariance
    //    def bar(x: X): Y

    //    Compilation Error: A should be a subtype of Y so that A as a subtype can be assigned to a supertype Y
    //    def barbar[A >: Y](a: A): X
}

object VarianceTypeCheckTesterService {
    class VarianceTypeCheckTesterServiceST extends VarianceTypeCheckTesterService[S, T] {
        override def foo(y: T): S = {
            println("foo: " + y)
            new S
        }

        override def foofoo[A >: S, B <: T](a: A): B = {
            //            Run time exception Here!!!!!! because of asInstanceOf
            (new T).asInstanceOf[B]
        }

        override def foobar[A >: S](a: A): U = {
            //            Compilation Error: new P
            println("Foobar: " + a)
            
            new V
        }
    }

    object VarianceTypeCheckTesterServiceST {
        private val st = new VarianceTypeCheckTesterServiceST


        def call = {
            //        Compilation Error: Since -T => all subtypes of T accepted
            //        val p = st.foo(new P)
            //        val q = st.foo(new Q)
            //        val r = st.foo(new R)
            //        val s = st.foo(new S)
            val t = st.foo(new T)
            val u = st.foo(new U)
            val v = st.foo(new V)
            val w = st.foo(new W)

            val p1 = st.foobar(new P)
            val q1 = st.foobar(new Q)
            val r1 = st.foobar(new R)
            val s1 = st.foobar(new S)
            val t1 = st.foobar(new T)
            val u1 = st.foobar(new U)
            val v1 = st.foobar(new V)
            val w1 = st.foobar(new W)
        }
    }

    class VarianceTypeCheckTesterServiceRU extends VarianceTypeCheckTesterService[R, U] {
        override def foo(y: U): S = new T

        override def foofoo[A >: S, B <: T](a: A): B = (new T).asInstanceOf[B]

        override def foobar[A >: R](a: A): R = new W
    }

    val st: VarianceTypeCheckTesterService[R, U] = new VarianceTypeCheckTesterServiceST

    def testST(): Unit = {
        val q: Q = st.foo(new V)

        val p: P = st.foobar(new Q)
        println(p)
        VarianceTypeCheckTesterServiceST.call
        //        Runtime error
        //        val v = st.foofoo[Q, V](new Q)
        //        println(v)
    }
}