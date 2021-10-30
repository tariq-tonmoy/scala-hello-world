package algorithms.collection

class Collection[T](elements: List[T]) {
    def getElements = elements

    private var defaultValue: T = _

    override def toString: String = elements.mkString(" ")

    private def processCollection(op: List[T] => List[T]): Collection[T] = {
        this.elements match {
            case Nil => new Collection[T](List())
            case elem => new Collection[T](op(elem))
        }
    }

    private def processCollection(op: List[T] => T): T = {
        this.elements match {
            case Nil => defaultValue
            case elem => op(elem)
        }
    }

    def init: Collection[T] = processCollection(_.init)

    def head: T = processCollection(_.head)

    def tail: Collection[T] = processCollection(_.tail)

    def last: T = processCollection(_.last)

    def reverse: Collection[T] = Collection.reverse(this.elements)

    def foldLeft(strat: Collection[T])(op: (Collection[T], T) => Collection[T]): Collection[T] = Collection.fold(start = strat, elements)(op)(_.head, _.tail)

    def foldRight(strat: Collection[T])(op: (Collection[T], T) => Collection[T]): Collection[T] = Collection.fold(start = strat, elements)(op)(_.last, _.init)
}

object Collection {
    def append[T](xs: List[T], ys: List[T]): Collection[T] = {
        xs match {
            case Nil => new Collection(ys)
            case x :: xk => new Collection[T](x :: append(xk, ys).getElements)
        }
    }

    def reverse[T](xs: List[T]): Collection[T] = {
        xs match {
            case Nil => Collection(List())
            case x :: Nil => Collection(List(x))
            case x :: xk => Collection.append(reverse(xk).getElements, List(x))
        }
    }

    def fold[T](start: Collection[T], xs: List[T])(op: (Collection[T], T) => Collection[T])(pickCurrent: (List[T]) => T, pickRest: (List[T]) => List[T]): Collection[T] = {
        xs match {
            case Nil => start
            case _ => {
                val operated = op(start, pickCurrent(xs))
                fold(operated, pickRest(xs))(op)(pickCurrent, pickRest)
            }
        }
    }

    def apply[T](xs: List[T]): Collection[T] = {
        new Collection[T](xs)
    }
}
