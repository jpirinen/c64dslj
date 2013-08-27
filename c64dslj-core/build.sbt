import AssemblyKeys._

organization := "org.pirinen"

name := "c64dslj"

version := "0.9"

scalaVersion := "2.9.2"

autoScalaLibrary := false

EclipseKeys.executionEnvironment := Some(EclipseExecutionEnvironment.JavaSE16)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

libraryDependencies += "com.novocode" % "junit-interface" % "0.10-M2" % "test->default"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

libraryDependencies += "com.google.guava" % "guava" % "13.0"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

parallelExecution in Test := false

assemblySettings

jarName in assembly := "c64dslj-with-dependencies-0.9.jar"
