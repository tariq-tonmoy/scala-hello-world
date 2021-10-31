package scalablecomponent.abstracttypes

class StringCell extends AbsCell {
    override type T = String
    override val init: String = {
        set(""); ""
    }
}
