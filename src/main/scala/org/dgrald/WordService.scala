package org.dgrald

import org.scalatra._
import org.scalatra.servlet.FileUploadSupport

class WordService extends WordServiceStack with FileUploadSupport with FlashMapSupport {

  get("/") {
    <form-group>
      <form method="post" enctype="multipart/form-data">
        <input type="file" name="thefile" />

        <input type="submit" />
      </form>
      <form method="post" enctype="multipart/form-data">
        <textarea name="filecontents" rows="25" cols="150"></textarea>

        <input type="submit" />
      </form>
    </form-group>
  }

  post("/") {
    val fileContents = params.get("filecontents") match {
      case Some(contents) => contents
      case None =>
        val file = fileParams("thefile")
        PdfTextParser.getTextFromPdf(file.get()) match {
          case Some(text) => text
          case _ => throw new Exception("Could not get file")
        }
    }

    val corrected = WordServiceCorrector.correct(fileContents)

    <form method="get" enctype="multipart/form-data">
      <div style="white-space: pre-wrap;">{corrected}</div>
      <input type="submit" value="Back"/>
    </form>
  }

}
