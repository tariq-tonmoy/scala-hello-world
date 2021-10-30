package options

class TestConstructor[A](s: Option[A]) {

    def this(v: A) = {
        this(Some(v))
    }

    def this() = {
        this(None)
    }
}
