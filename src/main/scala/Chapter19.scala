import typeparameterization.{IQueue, Queue, SlowAppendQueue, SlowHeadQueue}
import variance.*
import variance.container.DerivedContainer
import variance.hierarchy.*

import scala.collection.mutable.Seq as MutableSeq

object Chapter19 {

    def testInitialQueueOperations = {
        val slowAppendQueue: IQueue[Int] = new SlowAppendQueue[Int](List(1, 2, 3, 4, 5))
        val slowHeadQueue: IQueue[Int] = new SlowHeadQueue[Int](List(9, 10, 11, 12, 13))

        val slowAppendQueue1: IQueue[Int] = slowAppendQueue.enqueue(6)
        val slowHeadQueue1: IQueue[Int] = slowHeadQueue.enqueue(14)

        println("slowAppendQueue")
        println(slowAppendQueue1)
        println(slowAppendQueue1.head)
        val slowAppendQueueTail: IQueue[Int] = slowAppendQueue1.tail
        println(slowAppendQueueTail)

        println("slowHeadQueue")
        println(slowHeadQueue1)
        println(slowHeadQueue1.head)
        val slowHeadQueueTail: IQueue[Int] = slowHeadQueue1.tail
        println(slowHeadQueueTail)
    }

    def testQueueOperations = {
        var queue: IQueue[Int] = Queue()
        queue = queue.enqueue(1)
        queue = queue.enqueue(2)
        queue = queue.enqueue(3)
        queue = queue.enqueue(4)
        queue = queue.enqueue(5)
        println(queue.head)
        println(queue.head)

    }

    def testVarianceCompilation = {
        val queue: IQueue[String] = Queue[String]("Hello", "World")

        val subTypeTestU = new SubTypeUpperBoundTesterService[U]
        val subTypeTestT = new SubTypeUpperBoundTesterService[T]
        val subTypeTestW = new SubTypeUpperBoundTesterService[W]

        // Compilation Error
        // val subTypeTestS = new SubTypeUpperBoundTesterService[S]
        // val subTypeTestP = new SubTypeUpperBoundTesterService[P]


        val superTypeTestT = new SuperTypeLowerBoundTesterService[T]
        val superTypeTestS = new SuperTypeLowerBoundTesterService[S]
        val superTypeTestP = new SuperTypeLowerBoundTesterService[P]

        // Compilation Error
        // val superTypeTestU = new SuperTypeLowerBoundTesterService[U]
        // val superTypeTestW = new SuperTypeLowerBoundTesterService[W]


        // Compilation Error
        // val tightBoundTestP = new TightBoundTesterService[P]
        // val tightBoundTestQ = new TightBoundTesterService[Q]

        val tightBoundTestR = new TightBoundTesterService[R]
        val tightBoundTestS = new TightBoundTesterService[S]
        val tightBoundTestT = new TightBoundTesterService[T]

        // Compilation Error
        // val tightBoundTestU = new TightBoundTesterService[U]
        // val tightBoundTestV = new TightBoundTesterService[V]
        // val tightBoundTestW = new TightBoundTesterService[W]

        val arr = Array[Int](1, 2, 3, 4)
        arr(1) = 90

        // Compilation Error - Not Covariant
        // val arr2: Array[Any] = arr


        val list = List[Int](1, 2, 3, 4)
        // Compilation Error
        // list(1) = 90

        //Since list is immutable, list is covariant. no single element of list can be changed. that's not the case for Array and thus array is nonvariant
        val list2: List[Any] = list


        val mutableSeq: MutableSeq[Int] = MutableSeq(12, 42, 98)
        mutableSeq(0) = 0

        // Comppilation Error. Mutable Seq is also non variant
        // val mutableSeq2: MutableSeq[Any] = mutableSeq

        val CovarianceTesterService = new CovarianceTesterService[S]
        val covarianceTesterP: CovarianceTesterService[P] = CovarianceTesterService
        val covarianceTesterQ: CovarianceTesterService[Q] = CovarianceTesterService
        val covarianceTesterR: CovarianceTesterService[R] = CovarianceTesterService
        val covarianceTesterS1: CovarianceTesterService[S] = CovarianceTesterService
        // Compilation Error: Covariance Relationshio C[+X] -> C[SuperType] = C[SubType]
        // val covarianceTesterT: CovarianceTesterService[T] = CovarianceTesterService
        // val covarianceTesterU: CovarianceTesterService[U] = CovarianceTesterService
        // val covarianceTesterV: CovarianceTesterService[V] = CovarianceTesterService
        // val covarianceTesterW: CovarianceTesterService[W] = CovarianceTesterService

        val contravarianceTesterS = new ContravarianceTesterService[S]
        // Compilation Error: Contravariance relationship C[-X] -> C[SubType] = C[SuperType]
        // val contravarianceTestP: ContravarianceTesterService[P] = contravarianceTesterS
        // val contravarianceTestQ: ContravarianceTesterService[Q] = contravarianceTesterS
        // val contravarianceTestR: ContravarianceTesterService[R] = contravarianceTesterS
        val contravarianceTestS1: ContravarianceTesterService[S] = contravarianceTesterS
        val contravarianceTestT: ContravarianceTesterService[T] = contravarianceTesterS
        val contravarianceTestU: ContravarianceTesterService[U] = contravarianceTesterS
        val contravarianceTestV: ContravarianceTesterService[V] = contravarianceTesterS
        val contravarianceTestW: ContravarianceTesterService[W] = contravarianceTesterS

    }

    def testTypeRuleForFunction = {
        def testU2Q(u: U): Q = {
            new Q
        }

        def testR2V(r: R): V = {
            new V
        }

        // Compilation Error
        // val resR2V: V = testU2Q(new R)

        val resU2Q: Q = testR2V(new U)
    }


    @main def chapterNineteenTests = {
        testInitialQueueOperations
        testQueueOperations
        VarianceTypeCheckTesterService.testST()
        container.BaseContainer.testBaseContainer

        DerivedContainer.testDerivedContainer
    }
}
