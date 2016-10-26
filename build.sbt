val scala210 = "2.10.6"

lazy val chronoscala = (project in file("."))
  .settings(
    name := "chronoscala",

    organization := "jp.ne.opt",

    licenses += "MIT" -> url("https://raw.githubusercontent.com/opt-tech/chronoscala/master/LICENSE"),

    version := "0.1.1-SNAPSHOT",

    publishMavenStyle := true,

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.8", "2.12.0-RC1"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.2" % "test",

    TaskKey[Unit]("checkScalariform") := {
      val diff = "git diff".!!
      if (diff.nonEmpty) {
        sys.error("Working directory is dirty!\n" + diff)
      }
    }
  )
  .settings({
    val previousVersions = Set(0).map(patch => s"0.1.$patch")
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
  .settings(
    {
      import scalariform.formatter.preferences._
      import SbtScalariform._

      ScalariformKeys.preferences := ScalariformKeys.preferences.value
        .setPreference(DoubleIndentClassDeclaration, false)
        .setPreference(SpacesAroundMultiImports, false)
    }
  )
