name := "scala-forms"

scalaVersion := "2.11.7"

version := sys.props.getOrElse("tag", default = "0.0.0")
organization := "com.github.rehei"

resolvers += "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "staging" at "https://oss.sonatype.org/content/repositories/staging"
resolvers += "releases" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += Resolver.bintrayRepo("rehei", "maven")

libraryDependencies ++= {
  val liftVersion = "2.6.2"
  Seq(
    "com.github.rehei" %% "scala-macros" % "0.5.0",
    "commons-validator" % "commons-validator" % "1.5.0",
    "org.scala-lang.modules" %% "scala-xml" % "1.0.5", 
    "com.novocode" % "junit-interface" % "0.11" % "test"
  )
}

