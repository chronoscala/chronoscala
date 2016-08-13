val scala210 = "2.10.6"

lazy val chronoscala = (crossProject in file("."))
  .settings(
    name := "chronoscala",

    organization := "jp.ne.opt",

    licenses += "MIT" -> url("https://raw.githubusercontent.com/opt-tech/chronoscala/master/LICENSE"),

    version := "0.0.3-SNAPSHOT",

    publishMavenStyle := true,

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.8"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    libraryDependencies += "org.scalacheck" %%% "scalacheck" % "1.13.2" % "test"
  ).jsSettings(
    libraryDependencies += "org.scala-js" %%% "scalajs-java-time" % "0.2.0"
  )

val chronoscalaJVM = chronoscala.jvm
val chronoscalaJS = chronoscala.js

scalaSource in Compile := file("dummy")
scalaSource in Test := file("dummy")
PgpKeys.publishSigned := {}
PgpKeys.publishLocalSigned := {}
publishLocal := {}
publishArtifact in Compile := false
publish := {}
