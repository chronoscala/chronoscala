val scala210 = "2.10.6"

lazy val chronoscala = (project in file("."))
  .settings(
    name := "chronoscala",

    organization := "jp.ne.opt",

    licenses += "MIT" -> url("https://raw.githubusercontent.com/opt-tech/chronoscala/master/LICENSE"),

    version := "0.1.0",

    publishMavenStyle := true,

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.8"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.2" % "test"
  )
  .settings({
    val previousVersions = Set.empty[String]
    MimaPlugin.mimaDefaultSettings ++ Seq(
      mimaPreviousArtifacts := previousVersions.map {
        organization.value %% name.value % _
      },
      test in Test := {
        mimaReportBinaryIssues.value
        (test in Test).value
      }
    )
  })
