package typeparameterization

class SlowAppendQueue[T](private val elems: List[T]) extends IQueue[T] {
    override def head = elems.head

    override def tail = new SlowAppendQueue[T](elems.tail)

    override def enqueue[U >: T](elem: U): IQueue[U] = new SlowAppendQueue[U](elems ::: List[U](elem))

    override def toString: String = elems.mkString(" ")
}