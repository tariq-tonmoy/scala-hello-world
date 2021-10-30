package accessmodifiers

private class Class0(w: String, val x: String, private val y: String, private[this] val z: String) {

    def x1: String = "Class0 -> x1"

    private def y1: String = "Class0 -> y1"

    private[this] def z1: String = "Class0 -> z1"

    def testPrivateMembers(another: Class0) = {
        val concatOfThis: String = s"${this.w} ${this.x} ${this.y} ${this.z} ${this.x1} ${this.y1} ${this.z1}"
        val concatOfAnother: String = s"${another.x} ${another.y} ${another.x1} ${another.y1} ${another.z1}"
        val doesNotWorkOnAnother: String = "${another.w} ${another.z}"
    }
}

object Class0 {
    def testClass0 = {
        val class0: Class0 = new Class0("w", "x", "y", "z")
        val concat: String = s"${class0.y} ${class0.x1} ${class0.y1} ${class0.z1}"
        val doesNotWork: String = "${class0.w}  ${class0.z}"
    }
}

package packageA {
    class PackageAOuterClass {
        private[accessmodifiers] class PackageAInnerClassA {
            private[PackageAOuterClass] def foo: String = "packageA -> PackageAOuterClass -> PackageAInnerClassA -> foo"

            private[this] def bar: String = "packageA -> PackageAOuterClass -> PackageAInnerClassA -> bar"

            private[packageA] class InntermostClass {
                val _ = bar
                private[accessmodifiers] val x = new PackageAInnerClassB

                // Compilation Error
                // val _ = x.foo

                val _ = x.boo

                def checkPackageAInnerClassAPrivateThis(packageAInnerClassA: PackageAInnerClassA): Unit = {
                    if (bar == packageAInnerClassA.bar) {

                    }
                }
            }

            def checkPackageAInnerClassAPrivateThisInSameClass(packageAInnerClassA: PackageAInnerClassA): Unit = {
                if (this.bar == packageAInnerClassA.bar) {

                }
            }


        }

        private[PackageAOuterClass] class PackageAInnerClassB {
            // Compilation Error -> no enclosing class or object is named 'PackageAInnerClassA'
            // private[PackageAInnerClassA] def foo: String = "packageA -> PackageAOuterClass -> PackageAInnerClassB -> foo"

            private[PackageAInnerClassB] def foo: String = "packageA -> PackageAOuterClass -> PackageAInnerClassB -> foo"

            private[this] def bar: String = "packageA -> PackageAOuterClass -> PackageAInnerClassB -> bar"

            private def foobar: String = "packageA -> PackageAOuterClass -> PackageAInnerClassB -> foobar"

            def boo: String = "BOO"

            def testPackageAInnerClassBPrivateThis(packageAInnerClassB: PackageAInnerClassB) = {
                // Works on Scala 3
                val x = packageAInnerClassB.bar
                val x1 = this.bar
                val y = this.foobar
                val y1 = packageAInnerClassB.foobar

            }
        }

        val packageAInnerClassA = new PackageAInnerClassA
        val _ = packageAInnerClassA.foo
        val innerClassAInntermostClass = new packageAInnerClassA.InntermostClass
        val _ = innerClassAInntermostClass.x

    }
}

package packageB {
    class PackageBOuterClass {

        import packageA._

        val packageAOuterClass = new PackageAOuterClass
        val packageAInnerClassA = new packageAOuterClass.PackageAInnerClassA

        // Compilation Error
        // val _ = packageAInnerClassA.foo

        // Compilation Error
        // val innerClassAInntermostClass = new packageAInnerClassA.InntermostClass
        // val _ = innerClassAInntermostClass.x

    }
}