val scala210 = "2.10.7"

lazy val chronoscala = (project in file("."))
  .settings(
    name := "chronoscala",

    publishTo := Some(
      if (isSnapshot.value)
        Opts.resolver.sonatypeSnapshots
      else
        Opts.resolver.sonatypeStaging
    ),

    organization := "jp.ne.opt",

    licenses += "MIT" -> url("https://raw.githubusercontent.com/opt-tech/chronoscala/master/LICENSE"),

    version := "0.1.7-SNAPSHOT",

    publishMavenStyle := true,

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.12", "2.12.5", "2.13.0-M3"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",
      "org.scalatest" %% "scalatest" % "3.0.5-M1" % "test"
    ),

    TaskKey[Unit]("checkScalariform") := {
      val diff = sys.process.Process("git diff").!!
      if (diff.nonEmpty) {
        sys.error("Working directory is dirty!\n" + diff)
      }
    }
  )
  .settings({
    // Exclude 0.1.0 because 0.1.0 haven't released for Scala 2.12 .
    // Fix if minor version is bumped.
    val previousVersions = Set(1, 2, 3, 4, 5).map(patch => s"0.1.$patch")
    MimaPlugin.mimaDefaultSettings ++ Seq(
      mimaPreviousArtifacts := {
        CrossVersion.partialVersion(scalaVersion.value) match {
          case Some((2, v)) if v <= 12 =>
            previousVersions.map {
              organization.value %% name.value % _
            }
          case _ =>
            Set.empty
        }
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
        .setPreference(DoubleIndentConstructorArguments, false)
        .setPreference(SpacesAroundMultiImports, false)
    }
  )
