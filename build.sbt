val scala3Version = "3.1.1-RC1-bin-20211007-c041327-NIGHTLY"

lazy val root = project
  .in(file("."))
  .settings(
      name := "scala-hello-world",
      version := "0.1.0",

      scalaVersion := scala3Version,

      libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",

      libraryDependencies ++= Seq(
          "com.typesafe.akka" %% "akka-actor-typed" % "2.6.17",
          "ch.qos.logback" % "logback-classic" % "1.2.6" % Runtime
      )

  )
