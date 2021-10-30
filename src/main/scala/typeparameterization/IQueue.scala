package typeparameterization

trait IQueue[+T] {

    def head: T

    def tail: IQueue[T]

    def enqueue[U >: T](elem: U): IQueue[U]
}
