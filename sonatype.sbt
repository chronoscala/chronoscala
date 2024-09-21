import xerial.sbt.Sonatype.sonatype01

sonatypeProfileName := "io.github.chronoscala"
sonatypeCredentialHost := sonatype01
sonatypeRepository := "https://s01.oss.sonatype.org/service/local"

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
