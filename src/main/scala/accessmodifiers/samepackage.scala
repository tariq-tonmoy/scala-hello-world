package accessmodifiers

class SamePackageOuter {
    class SamePackageInner {
        class SamePackageInnerMost {
            def foo = "SamePackageOuter -> SamePackageInner -> SamePackageInnerMost -> foo"

            private def bar = "SamePackageOuter -> SamePackageInner -> SamePackageInnerMost -> bar"

            private val _ = _foo
            private val _ = _bar

            val p = (new SamePackagePrivateInnerMost).foo
            val _ = new SamePackagePrivateInnerMost


            // Compilation Error
            // val q = new SamePackagePrivateInnerMost

            // val _ = (new SamePackagePrivateInnerMost).bar
        }

        private class SamePackagePrivateInnerMost {
            def foo = "SamePackageOuter -> SamePackageInner -> SamePackagePrivateInnerMost -> foo"

            private def bar = "SamePackageOuter -> SamePackageInner -> SamePackagePrivateInnerMost -> bar"

            private val _ = _foo
            private val _ = _bar

            val _ = (new SamePackageInnerMost).foo
            val p = new SamePackageInnerMost
            val q = p.foo

            // Compilation Error
            // val _ = (new SamePackageInnerMost).bar
        }

        def _foo = "SamePackageOuter -> SamePackageInner -> foo"

        private def _bar = "SamePackageOuter -> SamePackageInner -> bar"

        private val _ = (new SamePackageInnerMost).foo
        // Compilation Error
        // private val _ = (new SamePackageInnerMost).bar

        private val _ = (new SamePackagePrivateInnerMost).foo
        // Compilation Error
        // val samePackagePrivateInnerMost = new SamePackagePrivateInnerMost
        // private val _ = (new SamePackagePrivateInnerMost).bar

        val _ = (new SamePackagePrivateInner)._foo
        // Compilation Error
        // val _ = (new SamePackagePrivateInner)._bar
        // val samePackagePrivateInner = new SamePackagePrivateInner
        // val _ = new samePackagePrivateInner.SamePackageInnerMost
    }

    private class SamePackagePrivateInner {
        class SamePackageInnerMost {
            def foo = "SamePackageOuter -> SamePackagePrivateInner -> SamePackageInnerMost -> foo"

            private def bar = "SamePackageOuter -> SamePackagePrivateInner -> SamePackageInnerMost -> bar"
        }

        private class SamePackagePrivateInnerMost {
            def foo = "SamePackageOuter -> SamePackagePrivateInner -> SamePackagePrivateInnerMost -> foo"

            private def bar = "SamePackageOuter -> SamePackagePrivateInner -> SamePackagePrivateInnerMost -> bar"
        }

        def _foo = "SamePackageOuter -> SamePackagePrivateInner -> foo"

        private def _bar = "SamePackageOuter -> SamePackagePrivateInner -> bar"

        private val _ = (new SamePackageInnerMost).foo
        // Compilation Error
        // private val _ = (new SamePackageInnerMost).bar

        private val _ = (new SamePackagePrivateInnerMost).foo
        // Compilation Error
        // private val _ = (new SamePackagePrivateInnerMost).bar

        private val samePackageInner = new SamePackageInner
        private val samePackageInnerSamePackageInnerMost = new samePackageInner.SamePackageInnerMost
        private val _ = samePackageInnerSamePackageInnerMost.foo

        // Compilation Error
        // private val _ = samePackageInnerSamePackageInnerMost.bar

    }

    val _ = (new SamePackageInner)._foo

    // Compilation Error
    // val _ = (new SamePackageInner)._bar
    // val _ = (new SamePackagePrivateInner)

}
