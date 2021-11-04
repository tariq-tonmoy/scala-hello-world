package concurrency.chapterthree.FileSystem

sealed trait FileState

class Idle extends FileState

class Deleting extends FileState

class Copying(val n: Int) extends FileState {
    private def getS = if (n > 1) "s" else ""

    override def toString: String = s"File State: Copying $n file${getS}"
}

class Creating extends FileState
