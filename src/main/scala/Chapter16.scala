import Layout._
import TraitQueue._
import algorithms.sorting._
import algorithms.collection._

object Chapter16 {

    def printQueueValues(queue: IntQueue): Unit = {
        val elem = queue.get()
        if (elem > Int.MinValue) {
            print(s"$elem  ")
            printQueueValues(queue)
        } else {
            println
        }
    }

    def testListCovariance = {
        val arrayElement: Element = Element.createElement(Array("Hello, ", "World!", "There", "You", "Are!"))
        val linearElement: Element = Element.createElement("Hello, World!, There you are!")
        val uniformElement: Element = Element.createElement('x', 20, 30)
        val concatenatedElement: Element = Element.createElement("Hello, ", "World!", "There", "You", "Are!")

        val listOfElements: List[Element] = List(arrayElement, linearElement, uniformElement, concatenatedElement)

        for (element <- listOfElements) {
            println(element.getContentsAsString)
            println
        }

        val basicIntQueue1: IntQueue = new BasicIntQueue
        basicIntQueue1.put(3)
        basicIntQueue1.put(4)
        basicIntQueue1.put(5)
        basicIntQueue1.put(6)

        val richIntQueue1: IntQueue = new RichIntQueue
        richIntQueue1.put(30)
        richIntQueue1.put(40)
        richIntQueue1.put(50)
        richIntQueue1.put(60)

        val basicIntQueue2: BasicIntQueue = new BasicIntQueue
        basicIntQueue2.put(7)
        basicIntQueue2.put(8)
        basicIntQueue2.put(9)

        val richIntQueue2: RichIntQueue = new RichIntQueue
        richIntQueue2.put(70)
        richIntQueue2.put(80)
        richIntQueue2.put(90)

        val intQueueList1: List[IntQueue] = List(basicIntQueue1, basicIntQueue2, richIntQueue1, richIntQueue2)

        val intQueueList2: List[IntQueue] = basicIntQueue1 :: richIntQueue1 :: basicIntQueue2 :: richIntQueue2 :: Nil

        val basicIntQueueList: List[BasicIntQueue] = List(basicIntQueue2)

        val richIntQueueList: List[RichIntQueue] = List(richIntQueue2)

        val intQueueList3: List[IntQueue] = basicIntQueueList ++ richIntQueueList ++ intQueueList1 ++ intQueueList2

        for (queue <- intQueueList3) {
            printQueueValues(queue)
        }

    }

    def testInsertionSort = {
        val insertionSort1: InsertionSort = InsertionSort(List(4, 3, 2, 10, 12, 1, 5, 6))
        val elements1 = insertionSort1.sortElements
        val elements11 = insertionSort1.sortElements
        println(elements1.mkString(" "))

        val insertionSort2: InsertionSort = InsertionSort(List(4))
        val elements2 = insertionSort2.sortElements

        println(elements2.mkString(" "))

        val insertionSort3: InsertionSort = InsertionSort(List(4, 1))
        val elements3 = insertionSort3.sortElements

        println(elements3.mkString(" "))

        val insertionSort4: InsertionSort = InsertionSort(List(4, 4))
        val elements4 = insertionSort4.sortElements

        println(elements4.mkString(" "))

        val insertionSort5: InsertionSort = InsertionSort(List(1, 4))
        val elements5 = insertionSort5.sortElements

        println(elements5.mkString(" "))
    }

    val lessThanComparer: (Int, Int) => Boolean = _ < _
    val greaterThanComparer: (Int, Int) => Boolean = _ > _

    def testMergeSort = {
        val mergeSort1: MergeSort[Int] = MergeSort[Int](List(38, 27, 43, 3, 9, 82, 10), lessThanComparer)
        println(mergeSort1.sortElements)

        val mergeSort2: MergeSort[Int] = MergeSort[Int](List(38, 27, 43, 3, 9, 82, 10), greaterThanComparer)
        println(mergeSort2.sortElements)

        val mergeSort3: MergeSort[Int] = MergeSort[Int](List(38), lessThanComparer)
        println(mergeSort3.sortElements)

        val mergeSort4: MergeSort[Int] = MergeSort[Int](List(38, 27), lessThanComparer)
        println(mergeSort4.sortElements)

        val mergeSort5: MergeSort[Int] = MergeSort[Int](List(27, 38), lessThanComparer)
        println(mergeSort5.sortElements)

        val mergeSort6: MergeSort[Int] = MergeSort[Int](List(38, 27), greaterThanComparer)
        println(mergeSort6.sortElements)

        val mergeSort7: MergeSort[Int] = MergeSort[Int](List(27, 38), greaterThanComparer)
        println(mergeSort7.sortElements)
    }

    def testCollectionOperations = {
        val collection: Collection[Char] = Collection.append("xyzk".toList, "abcd".toList)
        println(collection)

        val collection1: Collection[Char] = Collection.append("".toList, "a".toList)
        println(collection1)

        val collection2: Collection[Char] = Collection.append("a".toList, "".toList)
        println(collection2)

        val collection3: Collection[Int] = Collection.append(List[Int](), List[Int]())
        println(collection3)
        println(collection3.head)
        println(collection3.tail)

        val collection4: Collection[Element] = Collection.append(List[Element](), List[Element]())
        println(collection4.head)
        println(collection4.tail)

        println(collection.reverse)
        println(collection2.reverse)
        println(collection3.reverse)

        val collection5: Collection[Char] = collection.foldLeft(new Collection[Char](Nil))((start, current) => {
            Collection.append(List[Char](current), start.getElements)
        })

        val collection6: Collection[Char] = collection.foldRight(new Collection[Char](Nil))((start, current) => {
            Collection.append(start.getElements, List[Char](current))
        })

        println(collection5)
        println(collection6)

        val collection7: Collection[Int] = new Collection(List(1, 9, 2, 4, 7, 7)).foldLeft(new Collection[Int](List(0)))((start, current) => {
            new Collection[Int](List(start.getElements.sum + current))
        })

        println(collection7)
    }

    @main def chapterSixteenTests = {
        testListCovariance
        testInsertionSort
        testMergeSort
        testCollectionOperations
    }
}
