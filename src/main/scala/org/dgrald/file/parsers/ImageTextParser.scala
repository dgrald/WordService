package org.dgrald.file.parsers

import scalaj.http.Http
import org.json4s._
import org.json4s.native.JsonMethods._

/**
  * Created by dylangrald on 9/22/16.
  */
object ImageTextParser {

  implicit val jsonFormats = DefaultFormats

  def parseText(fileByteArray: Array[Byte]): String = {
    val username = "aglen"
    val licenseCode = "3FF24B1F-CDE7-4BEB-91CA-D63B99720711"

    val ocrURL = "http://www.ocrwebservice.com/restservices/processDocument?gettext=true"

    val timeout = 90000

    val body = Http(ocrURL)
      .auth(username, licenseCode)
      .timeout(timeout, timeout)
      .postData(fileByteArray)
      .header("Content-Type", "application/json")
      .header("Content-Length", fileByteArray.length.toString)
      .asString
      .body

    (parse(body) \ "OCRText")(0)(0).extract[String]
  }
}