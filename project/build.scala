import com.earldouglas.xwp.JettyPlugin
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object WordServiceBuild extends Build {
  val Organization = "org.dgrald"
  val Name = "Word Service"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.8"
  val ScalatraVersion = "2.4.1"

  lazy val project = Project (
    "word-service",
    file("."),
    settings = ScalatraPlugin.scalatraSettings ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      resolvers += "spray repo" at "http://repo.spray.io",
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "compile;container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "compile;container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
        "org.apache.pdfbox" % "pdfbox" % "2.0.2",
        "org.scalaj" %% "scalaj-http" % "2.3.0",
        "org.json4s" %% "json4s-native" % "3.4.1",
        "org.json4s" %% "json4s-jackson" % "3.4.1",
        "org.apache.poi" % "poi" % "3.14",
        "org.apache.poi" % "poi-scratchpad" % "3.14",
        "org.apache.poi" % "poi-ooxml" % "3.14",
        "commons-io" % "commons-io" % "2.5"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  ).enablePlugins(JavaAppPackaging, JettyPlugin)
}
