package typeparameterization

private class Queue[+T](private var leading: List[T], private var trailing: List[T]) extends IQueue[T] {
    private def mirror: Queue[T] = {
        if (leading.isEmpty) {
            new Queue[T](trailing.reverse, Nil)
        }
        else {
            this
        }
    }

    override def head: T = mirror.leading.head

    override def tail: IQueue[T] = {
        val mirrored = mirror
        new Queue[T](mirrored.leading.tail, mirrored.trailing)
    }

    override def enqueue[U >: T](elem: U): IQueue[U] = new Queue[U](leading, elem :: trailing)

    override def toString: String = (mirror.leading ::: trailing.reverse).mkString(" ")
}

object Queue {

    def apply[T](elems: T*): Queue[T] = {
        new Queue[T](elems.toList, Nil)
    }
}
