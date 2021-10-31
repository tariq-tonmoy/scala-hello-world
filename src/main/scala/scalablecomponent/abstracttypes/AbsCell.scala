package scalablecomponent.abstracttypes

abstract class AbsCell {
    type T
    val init: T
    private var value: T = {
        println(s"Initializing value: $init. This code will be executed once.")
        init
    }

    def get: T = value

    def set(x: T) = value = x
}
