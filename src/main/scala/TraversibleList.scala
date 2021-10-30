class TraversibleList {
    private var traverse: List[Double] = List[Double]();

    private def reduceLeftIrerator(actor: (Double, Double) => Double, lastResult: Double, tempIndex: Int): Double = {
        val res: Double = if (tempIndex == 0) {
            traverse(0)
        }
        else {
            val stageRes: Double = this.reduceLeftIrerator(actor, lastResult, tempIndex - 1)
            val actionRes: Double = actor(stageRes, traverse(tempIndex))
            actionRes
        }

        res
    }

    override def toString: String = {
        val elems = for (elem <- traverse) yield {
            elem
        }

        s"${elems.mkString(" ")}\n"
    }

    def reduceLeft(actor: (Double, Double) => Double): Double = {
        val len = this.traverse.length;
        this.reduceLeftIrerator(actor, traverse(len - 1), len - 1)
    }

    def unary_- = {
        val res = for {
            element <- this.traverse
        }
        yield {
            -element
        }

        res
    }


    private def compareAndSelect(val1: Double, val2: Double, comparisonActor: (Double, Double) => Boolean): Double = if comparisonActor(val1, val2) then val1 else val2

    private def isMaxValue(val1: Double, val2: Double): Boolean = val1 > val2

    private def isMinValue(val1: Double, val2: Double): Boolean = val1 <= val2

    private def compareLists(secondList: TraversibleList, comparisonActor: (Double, Double) => Boolean): Boolean = {
        val comparer: (Double, Double) => Double = compareAndSelect(_, _, comparisonActor)
        val leftReducedValue = this.reduceLeft(comparer)
        val secondLeftReducedValue = secondList.reduceLeft(comparer)
        comparisonActor(leftReducedValue, secondLeftReducedValue)
    }

    def hasMaxValue(secondList: TraversibleList): Boolean = compareLists(secondList, isMaxValue)

    def hasMinValue(secondList: TraversibleList): Boolean = compareLists(secondList, isMinValue)

    def operateOnList(secondList: TraversibleList, defaultValue: Double = 0.0)(operation: (Double, Double) => Double): TraversibleList = {
        var len1 = this.traverse.length
        var len2 = secondList.traverse.length

        val len = compareAndSelect(len1, len2, isMaxValue).asInstanceOf[Int]

        var results = for {
            i <- 0 until len
            num1 = if (i < len1) this.traverse(i) else defaultValue
            num2 = if (i < len2) secondList.traverse(i) else defaultValue
        }
        yield {
            operation(num1, num2)
        }

        TraversibleList(results: _*)
    }
}

object TraversibleList {
    def apply(elements: Double*): TraversibleList = {
        val traversibleList: TraversibleList = new TraversibleList
        for (element <- elements) {
            traversibleList.traverse = element :: traversibleList.traverse
        }
        traversibleList.traverse = traversibleList.traverse.reverse

        traversibleList
    }
}
