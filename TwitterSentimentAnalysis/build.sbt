name := "TwitterSentimentAnalysis"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  "mysql" % "mysql-connector-java" % "5.1.18",
  cache,
  "com.google.inject" % "guice" % "4.0-beta"
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4"
)     

play.Project.playJavaSettings
