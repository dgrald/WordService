package org.dgrald

import org.dgrald.word.correctors.{WordServiceCorrector, PdfTextParser}
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
        <input type="checkbox" name="linebreaks" checked="true"/>Create new lines<br/>
        <textarea name="filecontents" rows="25" cols="150"></textarea>
        <button>Submit</button>
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

    val newLinesParam = params.getOrElse("linebreaks", "false")
    val createNewLines = newLinesParam == "on"

    val corrected = WordServiceCorrector.correct(fileContents, createNewLines)

    <form method="get" enctype="multipart/form-data">
      <div>
        <input type="submit" value="Back"/>
      </div>
      <div>
        <textarea rows="25" cols="150" wrap="soft" autofocus="true" onfocus="this.select()">{corrected}</textarea>
      </div>
    </form>
  }

}
