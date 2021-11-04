package concurrency.chapterthree

import concurrency.chapterthree.FileSystem._

import java.util.concurrent.atomic.*

object Chapter3 {

    def asyncFunc(id: Int)(): Unit = {
        println(s"Thread #$id started. Waiting for 10 seconds")
        Thread.sleep(10000)
        println(s"Thread #$id ended.")
    }

    def createAsyncFunctions(functionsLeft: Int, functions: List[() => Unit]): List[() => Unit] = {
        if (functionsLeft == 0) {
            functions
        } else {
            createAsyncFunctions(functionsLeft - 1, asyncFunc(functionsLeft) :: functions)
        }
    }

    def testExecutors = {
        val executor = new TestExecutors(8)
        executor.execute {
            createAsyncFunctions(1000000, Nil)
        }
    }

    def testAtomics = {
        val testAtomics = new TestAtomics(90L)
        val testAtomicsCompareFalse = testAtomics.compareAndSwap(new TestAtomics(testAtomics.getValue + 1), new TestAtomics(95L))
        println(s"testAtomicsCompareFalse: $testAtomicsCompareFalse; currentValue = ${testAtomics.getValue}")

        val testAtomicsCompareTrue = testAtomics.compareAndSwap(testAtomics, new TestAtomics(95L))
        println(s"testAtomicsCompareTrue: $testAtomicsCompareTrue; currentValue = ${testAtomics.getValue}")

        val javaAtomic = new AtomicLong(30L)
        val javaAtomicFalse = javaAtomic.compareAndSet(31L, 90L)
        println(s"javaAtomicFalse: $javaAtomicFalse; currentValue = ${javaAtomic.get()}")

        val javaAtomicTrue = javaAtomic.compareAndSet(30L, 91L)
        println(s"javaAtomicTrue: $javaAtomicTrue; currentValue = ${javaAtomic.get()}")

    }

    def testFileIdleToDeletion(fileEntityIdle: FileEntity)(): Unit = {
        val resIdleToDeleting = FileOperations.prepateFileForDeletion(fileEntityIdle)
        println(s"resIdleToDeleting: $resIdleToDeleting")
    }

    def testLazyVal(x: Int)(): Unit = {
        xVar = x
    }

    var xVar: Int = _

    lazy val yVal = xVar

    def testFileStates = {
        val fileEntityIdle = new FileEntity("file1", new AtomicReference[FileState](new Idle))
        val resIdleToDeleting = FileOperations.prepateFileForDeletion(fileEntityIdle)
        println(s"resIdleToDeleting: $resIdleToDeleting")

        val fileEntityDeleting = fileEntityIdle
        val resDeletingToCopying = FileOperations.prepateFileForDeletion(fileEntityDeleting)
        println(s"resDeletingToCopying: $resDeletingToCopying")

        val fileEntityIdleForCopying = new FileEntity("file2", new AtomicReference[FileState](new Idle))
        val resIdleToCopying = FileOperations.prepareFileForCopying(fileEntityIdleForCopying)
        println(s"resIdleToCopying: $resIdleToCopying")
        println(fileEntityIdleForCopying.state.get())
        val resCopyingToCopying = FileOperations.prepareFileForCopying(fileEntityIdleForCopying)
        println(s"resCopyingToCopying: $resCopyingToCopying")
        println(fileEntityIdleForCopying.state.get())

        println()
        println()

        val fileEntityIdleForThread = new FileEntity("file1", new AtomicReference[FileState](new Idle))

        var listOfFunc = List[() => Unit]();
        for (i <- 0 until 3000) {
            listOfFunc = List[() => Unit](testFileIdleToDeletion(fileEntityIdleForThread), testLazyVal(i)) ::: listOfFunc
        }
        val executor = new TestExecutors(800)
        executor.execute(listOfFunc)

        Thread.sleep(3000)
        println(s"Value of lazyVal: $yVal")
    }


    @main def chapterThreeTests = {
        testExecutors
        testAtomics
        testFileStates
    }
}
