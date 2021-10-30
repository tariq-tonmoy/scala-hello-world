package algorithms.sorting

class MergeSort[T](private val elements: List[T], private val comparer: (T, T) => Boolean) {
    override def toString: String = elements.mkString(" ")

    val sortElements: List[T] = {
        sort(elements)
    }

    private def getNewElement(left: List[T], right: List[T]): Tuple3[List[T], List[T], T] = {
        (left, right) match {
            case (_, Nil) => (left.tail, right, left.head)
            case (Nil, _) => (left, right.tail, right.head)
            case _ if comparer(left.head, right.head) => (left.tail, right, left.head)
            case _ => (left, right.tail, right.head)
        }
    }

    private def merge(left: List[T], right: List[T], merged: List[T]): List[T] = {
        if (left.isEmpty && right.isEmpty) {
            merged
        }
        else {
            val (newLeft, newRight, elem) = getNewElement(left, right)
            merge(newLeft, newRight, merged ::: (elem :: Nil))
        }
    }

    private def sort(elems: List[T]): List[T] = {
        if (elems.isEmpty || elems.length == 1) {
            elems
        }
        else {
            val n = elems.length / 2
            val splitElems = elems.splitAt(n)

            val left = sort(splitElems._1)
            val right = sort(splitElems._2)

            merge(left, right, List())
        }
    }
}

object MergeSort {
    def apply[T](elements: List[T], comparer: (T, T) => Boolean): MergeSort[T] = {
        new MergeSort(elements, comparer)
    }
}
