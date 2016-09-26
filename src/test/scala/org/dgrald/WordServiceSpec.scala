package org.dgrald

import java.io.ByteArrayInputStream

import org.apache.poi.xwpf.extractor.XWPFWordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.scalatra.test.specs2._

class WordServiceSpec extends MutableScalatraSpec {

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
  }
}
