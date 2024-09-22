import xerial.sbt.Sonatype.sonatypeCentralHost

ThisBuild / sonatypeProfileName := "io.github.chronoscala"
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

pomExtra in Global := {
  <url>https://github.com/chronoscala/chronoscala</url>
    <scm>
      <connection>"scm:git:git@github.com:chronoscala/chronoscala.git"</connection>
      <url>git@github.com:chronoscala/chronoscala.git</url>
    </scm>
    <developers>
      <developer>
        <id>ocadaruma</id>
        <name>Haruki Okada</name>
      </developer>
      <developer>
        <id>sh0hei</id>
        <name>Shohei Shimomura</name>
      </developer>
    </developers>
}
