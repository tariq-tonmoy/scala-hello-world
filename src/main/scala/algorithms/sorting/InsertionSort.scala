package algorithms.sorting

class InsertionSort(private val elements: List[Int]) {

    override def toString: String = elements.mkString(" ")

    private def this(elements: List[Int], sorted: List[Int]) = {
        this(elements)
    }

    val sortElements: List[Int] = {
        sort(elements.slice(0, 1), 1)
    }

    private def insert(sortedElements: List[Int], currentElement: Int): List[Int] = {
        if (sortedElements.isEmpty || sortedElements.head > currentElement) {
            currentElement :: sortedElements
        } else {
            sortedElements.head :: insert(sortedElements.tail, currentElement)
        }
    }

    private def sort(sortedElements: List[Int], currentIdx: Int): List[Int] = {
        if (currentIdx >= elements.length) {
            sortedElements
        } else {
            sort(insert(sortedElements, elements(currentIdx)), currentIdx + 1)
        }
    }
}

object InsertionSort {
    def apply(elements: List[Int]): InsertionSort = {
        new InsertionSort(elements)
    }
}
