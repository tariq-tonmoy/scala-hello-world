package scalablecomponent.abstracttypes

class DoubleCell extends AbsCell {
    override type T = Double
    override val init: Double = {
        set(0.01d); 0.01d
    }
}
