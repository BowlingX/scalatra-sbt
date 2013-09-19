scalatra-sbt
============

An SBT Plugin to for scalatra


This plugin adds a browse task, to open the current project in a browser. It depends on xsbt-web-plugin so you don't need to specify that explicitly.

It also has a JRebel plugin that will generate a rebel.xml file for your project so you can use scalatra with jrebel.


A third plugin is a mini war-overlay plugin. it allows you to depend on war files and it will copy the static files out of those wars and into the current project.
You can use it to depend on jquery-atmosphere for example.

And lastly there is a plugin that unifies all the plugins in this project by grouping their settings.



DistPlugin
==========



```scala

/**
 * This describes our Main Entry point to launch an Application with embedded jetty server
 * Launches an embedded Jetty Server
 */
class ApplicationLauncher(name: String, version: String, datetime: String) extends App with Logging {

  import logger._

  val thisArgs = if (0 == args.size) {
    // Default Arguments:
    Array("8080")
  } else args

  val Array(port) = thisArgs

  val server = new Server(port.toInt)

  val context = getClass.getClassLoader.getResource("webapp").toExternalForm

  info("Starting %s; Version: %s, Build @ %s" format(name, version, datetime))
  info("Booting WAR Context: %s" format context)

  val root = new WebAppContext(context, "/")
  // Disable Directory listing
  root.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false")
  Runtime.getRuntime.addShutdownHook(new Thread() {
    override def run() {
      info("Stopping %s" format name)
      server.stop()
    }
  })

  server.setHandler(root)
  server.start()
  server.join()

}



```

