organization := "com.ovoenergy"
bintrayOrganization := Some("ovotech")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scalaVersion := "2.12.4"
crossScalaVersions := Seq("2.11.12", scalaVersion.value)
releaseCrossBuild := true

scalacOptions += "-language:higherKinds"

libraryDependencies ++= Seq(
  "is.cir" %% "ciris-core" % "0.8.1"
)

// Tut
enablePlugins(TutPlugin)
tutSourceDirectory := baseDirectory.value / "src" / "main" / "tut"
tutTargetDirectory := baseDirectory.value
