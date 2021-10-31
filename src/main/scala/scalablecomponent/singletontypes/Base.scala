package scalablecomponent.singletontypes

class Base {
    protected var x: Int = 0

    def incr: this.type = {
        x = x + 1
        this
    }

    override def toString: String = "Base"
}
