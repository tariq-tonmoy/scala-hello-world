package abstracts

class Concrete extends Abstract {
    override type T = String

    override def transform(x: String): String = x + x

    override val initial: String = "Hello, "

    //  Compilation Error: error overriding variable current in trait Abstract of type String; variable current of type String cannot override a mutable variable
    //  override var current: String = initial
    //  override var implementedCurrent: String = "Hello, World"

    var current: String = "World!"

    override val defCanOverrideAsVal: String = "Def Can be overridden as val"

    override val valCannotOverrideAsDef: String = "Val cannot be overridden as def"

    //    Compilation Error: "Val cannot be overridden as def"
    //    override def valCannotOverrideAsDef: String = "Val cannot be overridden as def"
}
