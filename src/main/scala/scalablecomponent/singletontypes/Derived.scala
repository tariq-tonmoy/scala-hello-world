package scalablecomponent.singletontypes

class Derived extends Base {
    def decr: this.type = {
        x = x - 1
        this
    }

    override def toString: String = "Derived"
}
