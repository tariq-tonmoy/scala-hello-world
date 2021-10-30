package TraitQueue

trait IntComparer extends Ordered[IntComparer] {
    def comparisonParamProvider: () => Int
    
    override def compare(that: IntComparer): Int = {
        val thisComparisonParam: Int = this.comparisonParamProvider()
        val thatComparisonProvider: Int = that.comparisonParamProvider()

        thisComparisonParam - thatComparisonProvider
    }
}
