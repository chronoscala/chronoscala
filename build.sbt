import com.typesafe.tools.mima.core.{DirectMissingMethodProblem, ProblemFilters}

Global / onChangedBuildSource := ReloadOnSourceChanges

skip in publish := true

lazy val chronoscala = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
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

    version := "1.0.0",

    publishMavenStyle := true,

    scalaVersion := "2.12.12",

    crossScalaVersions := Seq("2.11.12", "2.12.12", "2.13.4"),

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

    TaskKey[Unit]("checkScalariform") := {
      val diff = sys.process.Process("git diff").!!
      if (diff.nonEmpty) {
        sys.error("Working directory is dirty!\n" + diff)
      }
    }
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % "1.15.1" % Test,
      "org.scalatest" %%% "scalatest" % "3.2.3" % Test
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "io.github.cquiroz" %%% "scala-java-time" % "2.0.0",
      "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.0.0"
    )
  )
  .settings({
    val previousVersions = (0 to -1).map(patch => s"1.0.$patch").toSet
    Seq(
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
  ).enablePlugins(MimaPlugin)
