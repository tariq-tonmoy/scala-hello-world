package bobsrockets {

    package navigation {
        class Navigator {

        }

        class StarMap

        package launch {
            class Booster //booster1
        }

        class MissionControl {
            val booster1 = new launch.Booster
            val booster2 = new bobsrockets.launch.Booster
            val booster3 = new _root_.launch.Booster
        }

    }

    package launch {
        class Booster //booster2
    }

    class Ship(val shipName: String) {

        def this() = {
            this("Default Ship")
        }

        val nav = new navigation.Navigator
    }

    package maintenance {
        class MaintainService {
            def maintain(ship: Ship) = new Ship(ship.shipName)
        }
    }

}

package launch {
    // Booster is already defined as class Booster in .\bobsrockets.scala
    //class Booster
}

package bobsrockets.fleets {
    class Fleet {
        def addShip = {
            import bobsrockets.Ship
            new Ship
        }
    }
}