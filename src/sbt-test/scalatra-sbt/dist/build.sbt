import org.scalatra.sbt.DistPlugin._

seq(webSettings :_*)

seq(webDistSettings:_*)

name := "DistPluginTestApp"


mainClass := Some("com.yourcompany.Main")

libraryDependencies ++= Seq(
     "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided, container",
     "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container"
)


version := "0.1"
