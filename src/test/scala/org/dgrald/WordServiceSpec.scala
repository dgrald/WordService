package org.dgrald

import java.io.ByteArrayInputStream

import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.json4s._
import org.json4s.native.JsonMethods._
import org.scalatra.test.specs2._

class WordServiceSpec extends MutableScalatraSpec {

  implicit val jsonFormats = DefaultFormats

  addServlet(classOf[WordService], "/*")

  "POST /wordfile" should {
    "return a file with the input contents" in {
      val fileContents = SomeRandom.string
      post("/wordfile", ("worddoccontents", fileContents)) {
        status must_== 200
        val doc = new XWPFDocument(new ByteArrayInputStream(bodyBytes))
        val extractor = new XWPFWordExtractor(doc)
        extractor.getText.trim must_== fileContents
      }
    }

    "maintain line breaks" in {
      val input = "* Excessive calcification or thickening of mitral valve annulus, severe mitral stenosis, fused commissures, valvular vegetation or mass\n* LVEDD >7cm\n* LVOT obstruction\n* Severe right ventricular dysfunction\n* Stroke within 90 days; transischemic attack or myocardial infarction within 30 days of the index procedure"
      post("/wordfile", ("worddoccontents", input)) {
        status must_== 200
        val doc = new XWPFDocument(new ByteArrayInputStream(bodyBytes))
        val extractor = new XWPFWordExtractor(doc)
        extractor.getText.trim must_== input
      }
    }
  }

  "POST at the root" should {
    "return the JSON with the input and output" in {
      val input = "Input string."
      post("/", ("returnjson", "true"), ("filecontents", input)) {
        status must_== 200

        val bodyJson = parse(body)
        (bodyJson \ "input").extract[String] must_== input
        (bodyJson \ "output").extract[String] must_== "Input string"
      }
    }
  }
}
