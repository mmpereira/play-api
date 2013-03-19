import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-api"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "commons-dbcp" % "commons-dbcp" % "1.4",
    "org.springframework" % "spring-context" % "3.1.2.RELEASE",
    "org.springframework" % "spring-jdbc" % "3.1.2.RELEASE",
    "com.genebio.nextprot" % "services" % "0.0.1-SNAPSHOT",

    javaCore
    //javaJdbc,
    //javaEbean
  )

  

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
    resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"
  
  )

}
