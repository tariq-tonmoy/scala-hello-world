package concurrency.chapterthree

class TestAtomics(private var value: Long) {
    def compareAndSwap(oldVal: TestAtomics, newVal: TestAtomics) = {
        this.synchronized {
            if (oldVal.value != this.value)
                false
            else {
                value = newVal.value
                true
            }
        }
    }

    def getValue = this.value
}
