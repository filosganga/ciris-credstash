organization := "com.ovoenergy"
bintrayOrganization := Some("ovotech")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scalaVersion := "2.12.3"
crossScalaVersions := Seq("2.11.11", scalaVersion.value)
releaseCrossBuild := true

libraryDependencies ++= Seq(
  "is.cir" %% "ciris-core" % "0.4.0"
)

// Tut
enablePlugins(TutPlugin)
tutSourceDirectory := baseDirectory.value / "src" / "main" / "tut"
tutTargetDirectory := baseDirectory.value