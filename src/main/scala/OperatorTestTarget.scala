case class OperatorTestTarget(internalValue: Double) {

    def infixAddition(value: Double): Double = {
        this.internalValue + value
    }

    private def infixAddition(addedRes: Double, currentIdx: Int, values: Double*): Double = {
        if (currentIdx == values.length - 1) {
            this infixAddition values(currentIdx)
        }
        else {
            var res = this.infixAddition(addedRes, currentIdx + 1, values: _*);
            res + values(currentIdx)
        }
    }

    def infixAddition(values: Double*): Double = {
        this.infixAddition(this.internalValue, 0, values: _*)
    }

    def unary_- = {
        -this.internalValue
    }


    def unary_-(target: OperatorTestTarget): Double = {
        -target.internalValue
    }

    def +(valueA: OperatorTestTarget): OperatorTestTarget = {
        val res = OperatorTestTarget(this.internalValue + valueA.internalValue)
        println("+ Passed Value: " + valueA.internalValue + "; Internal Value: " + this.internalValue + "; Res: " + res.internalValue)

        res
    }

    def *(valueB: OperatorTestTarget): OperatorTestTarget = {
        val res = OperatorTestTarget(this.internalValue * valueB.internalValue)
        println("* Passed Value: " + valueB.internalValue + "; Internal Value: " + this.internalValue + "; Res: " + res.internalValue)

        res
    }

    def ::(valueC: OperatorTestTarget): OperatorTestTarget = {
        val res = OperatorTestTarget(this.internalValue + valueC.internalValue * 2)
        println(":: Passed Value: " + valueC.internalValue + "; Internal Value: " + this.internalValue + "; Res: " + res.internalValue)

        res
    }

    def add(valueD: OperatorTestTarget): OperatorTestTarget = {
        val res = OperatorTestTarget(this.internalValue + valueD.internalValue)
        println("add Passed Value: " + valueD.internalValue + "; Internal Value: " + this.internalValue + "; Res: " + res.internalValue)

        res
    }
}

object OperatorTestTarget {
    def apply(value: Double): OperatorTestTarget = {
        new OperatorTestTarget(value)
    }

    def getValue(target: OperatorTestTarget): Double = target.internalValue
}
