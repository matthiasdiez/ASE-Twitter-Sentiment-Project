name := "TwitterSentimentAnalysis"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "mysql" % "mysql-connector-java" % "5.1.18",
  cache,
  "com.google.inject" % "guice" % "4.0-beta"
)     

play.Project.playJavaSettings
