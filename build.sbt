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

    version := "0.1.5-SNAPSHOT",

    publishMavenStyle := true,

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.12", "2.12.4"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.5" % "test",

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
    val previousVersions = Set(1, 2, 3, 4).map(patch => s"0.1.$patch")
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
        .setPreference(DoubleIndentConstructorArguments, false)
        .setPreference(SpacesAroundMultiImports, false)
    }
  )
