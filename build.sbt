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

    version := "0.3.0",

    publishMavenStyle := true,

    scalaVersion := scala210,

    crossScalaVersions := Seq(scala210, "2.11.12", "2.12.7", "2.13.0-M4"),

    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),

    scalacOptions in (Compile, doc) ++= {
      val tree = sys.process.Process("git rev-parse HEAD").lineStream_!.head
      Seq(
        "-sourcepath",
        (baseDirectory in LocalRootProject).value.getAbsolutePath,
        "-doc-source-url",
        s"https://github.com/opt-tech/chronoscala/tree/${tree}€{FILE_PATH}.scala"
      )
    },

    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.14.0" % "test",
      "org.scalatest" %% "scalatest" % "3.0.6-SNAP2" % "test"
    ),

    TaskKey[Unit]("checkScalariform") := {
      val diff = sys.process.Process("git diff").!!
      if (diff.nonEmpty) {
        sys.error("Working directory is dirty!\n" + diff)
      }
    }
  )
  .settings({
    val previousVersions = Set.empty
    MimaPlugin.mimaDefaultSettings ++ Seq(
      mimaPreviousArtifacts := {
        previousVersions.map {
          organization.value %% name.value % _
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
