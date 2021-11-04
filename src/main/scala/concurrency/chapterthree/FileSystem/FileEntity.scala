package concurrency.chapterthree.FileSystem

import java.util.concurrent.atomic._

class FileEntity(val name: String, var state: AtomicReference[FileState])
