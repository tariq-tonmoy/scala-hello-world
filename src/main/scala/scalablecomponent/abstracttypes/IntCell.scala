package scalablecomponent.abstracttypes

class IntCell extends AbsCell {
    override type T = Int
    override val init: Int = {
        set(0); 0
    }
}
