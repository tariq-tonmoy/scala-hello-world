package typeparameterization

class SlowHeadQueue[T](private val elems: List[T]) extends IQueue[T] {

    override def head: T = elems.last

    override def tail: IQueue[T] = new SlowHeadQueue[T](elems.init)

    override def enqueue[U >: T](elem: U): IQueue[U] = new SlowHeadQueue[U](elem :: elems)

    override def toString: String = elems.mkString(" ")
}
