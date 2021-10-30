package accessmodifiers

package packageA {
    abstract class BaseClassPackageA {
        protected def foo: String

        protected[packageA] def bar = "packageA -> BaseClassPackageA -> bar"
    }

    class DerivedClassPackageA extends BaseClassPackageA {
        override def foo = "packageA -> BaseClassPackageA -> DerivedClassPackageA"
    }

    protected class ProtectedDerivedClassPackageA extends BaseClassPackageA {
        override def foo = "packageA -> BaseClassPackageA -> ProtectedDerivedClassPackageA"
    }

    package packageA1 {
        protected[packageA] class ProtectedDerivedClassAPackageA1 extends BaseClassPackageA {
            override def foo = "packageA -> BaseClassPackageA -> packageA1 -> ProtectedDerivedClassAPackageA1"
        }

        protected class ProtectedDerivedClassBPackageA1 extends BaseClassPackageA {
            override def foo = "packageA -> BaseClassPackageA -> packageA1 -> ProtectedDerivedClassBPackageA1"
        }
    }

    class OtherClassPackageA {

        import packageA1._

        val derivedClassPackageA: BaseClassPackageA = new DerivedClassPackageA

        // Compilation Error
        // val x = derivedClassPackageA.foo

        val protectedDerivedClassPackageA: BaseClassPackageA = new ProtectedDerivedClassPackageA
        // Compilation Error
        // val y = protectedDerivedClassPackageA.foo

        val protectedDerivedClassAPackageA1: BaseClassPackageA = new ProtectedDerivedClassAPackageA1


        // Compilation Error: ClassB access modifier is not protected[]
        // val protectedDerivedClassBPackageA1: BaseClassPackageA = new ProtectedDerivedClassBPackageA1
    }
}

package packageB {

    import accessmodifiers.packageA._

    class OtherClassPackageB {
        val derivedClassPackageA: BaseClassPackageA = new DerivedClassPackageA

        // Compilation Error
        // val x = derivedClassPackageA.foo

        // Compilation Error
        // val protectedDerivedClassPackageA: BaseClassPackageA = new ProtectedDerivedClassPackageA
        // val y = protectedDerivedClassPackageA.foo
    }
}

package packageC {

    import accessmodifiers.packageA._

    class DerivedClassPackageC extends BaseClassPackageA {
        override def foo = bar
    }

}