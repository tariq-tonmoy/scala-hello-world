package concurrency.chapterthree.FileSystem

object FileOperations {

    def prepateFileForDeletion(fileEntity: FileEntity): Boolean = {
        val fileState = fileEntity.state.get()
        fileState match {
            case deleting: Deleting => false
            case idle: Idle => {
                // replacing fileState with new Idle will not work. compareAndSet matches Reference Equality, not overriden equal method
                // if (fileEntity.state.compareAndSet(new Idle, new Deleting))

                if (fileEntity.state.compareAndSet(fileState, new Deleting))
                    true
                else
                    prepateFileForDeletion(fileEntity)
            }
            case copying: Copying => {
                println("File Status is now copying")
                false
            }
            case creating: Creating => {
                println("File Status is now Creating")
                false
            }
        }
    }

    def prepareFileForCopying(fileEntity: FileEntity): Boolean = {
        val fileState = fileEntity.state.get()

        fileState match {
            case creating: Creating => {
                println("File Status is now Creating")
                false
            }
            case deleting: Deleting => {
                println("File Status is now Deleting")
                false
            }
            case idle: Idle => {
                if (fileEntity.state.compareAndSet(fileState, new Copying(1)))
                    true
                else
                    prepareFileForCopying(fileEntity)
            }
            case copying: Copying => {
                // copying.n will not work. make `n` immutable, so that new Copying must be created. O/W other threads
                // will find the copying reference to be same, resulting in race condition
                if (fileEntity.state.compareAndSet(fileState, new Copying(copying.n + 1)))
                    true
                else
                    prepareFileForCopying(fileEntity)
            }
        }
    }
}
