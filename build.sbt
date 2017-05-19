enablePlugins(ScalaJSPlugin)

name := "ScalaJSTodo"

version := "1.0"

scalaVersion := "2.12.2"

scalaJSModuleKind := ModuleKind.CommonJSModule

libraryDependencies ++= Seq(
  "io.github.cquiroz" %%% "scala-java-time" % "2.0.0-M11"
)
