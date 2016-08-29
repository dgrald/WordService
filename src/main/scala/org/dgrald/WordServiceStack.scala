package org.dgrald

import org.scalatra._
import org.scalatra.servlet.{MultipartConfig, FileUploadSupport}
import scalate.ScalateSupport
import org.fusesource.scalate.{ TemplateEngine, Binding }
import org.fusesource.scalate.layout.DefaultLayoutStrategy
import javax.servlet.http.HttpServletRequest
import collection.mutable

trait WordServiceStack extends ScalatraServlet with ScalateSupport with FileUploadSupport {

  configureMultipartHandling(MultipartConfig(maxFileSize = Some(3*1024*1024)))

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}
