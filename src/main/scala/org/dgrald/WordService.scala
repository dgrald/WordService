package org.dgrald

import org.dgrald.word.correctors.WordServiceCorrector
import org.scalatra._
import org.scalatra.servlet.FileUploadSupport

class WordService extends WordServiceStack with FileUploadSupport with FlashMapSupport {

  get("/") {
    <form-group>
      <h2>Text</h2>
      <form method="post" enctype="multipart/form-data">
        <input type="checkbox" name="linebreaks" checked="true"/>Create new lines<br/>
        <textarea name="filecontents" rows="25" cols="150"></textarea>
        <input type="submit" />
        <table>
          <tr>
            <th>Replace</th>
            <th>Replace with</th>
          </tr>
          <tr>
            <td><input type="text" name="replace1"></input></td>
            <td><input type="text" name="replacement1"></input></td>
          </tr>
          <tr>
            <td><input type="text" name="replace2"></input></td>
            <td><input type="text" name="replacement2"></input></td>
          </tr>
          <tr>
            <td><input type="text" name="replace3"></input></td>
            <td><input type="text" name="replacement3"></input></td>
          </tr>
        </table>
      </form>
      <h2>File</h2>
      <form method="post" enctype="multipart/form-data">
        <input type="file" name="thefile" />
        <input type="checkbox" name="linebreaks" checked="true"/>Create new lines
        <table>
          <tr>
            <th>Replace</th>
            <th>Replace with</th>
          </tr>
          <tr>
            <td><input type="text" name="replace1"></input></td>
            <td><input type="text" name="replacement1"></input></td>
          </tr>
          <tr>
            <td><input type="text" name="replace2"></input></td>
            <td><input type="text" name="replacement2"></input></td>
          </tr>
          <tr>
            <td><input type="text" name="replace3"></input></td>
            <td><input type="text" name="replacement3"></input></td>
          </tr>
        </table>
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

    val newLinesParam = params.getOrElse("linebreaks", "false")
    val createNewLines = newLinesParam == "on"

    def extractReplacementInstructions: List[(String, String)] = {
      def extractReplacement(num: Int): (String, String) = {
        (params.getOrElse(s"replace$num", ""), params.getOrElse(s"replacement$num", ""))
      }

      val instructions = (for {
        num <- Range(1,3)
      } yield extractReplacement(num)).toList

      instructions.filter(pair => pair match {case (first: String, second: String) => first != "" && second != ""})
    }

    val otherInstructions = extractReplacementInstructions

    val corrected = WordServiceCorrector.correct(fileContents, createNewLines, otherInstructions)

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
