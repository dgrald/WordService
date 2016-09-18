package org.dgrald

import org.dgrald.word.correctors.WordServiceCorrector
import org.scalatra._
import org.scalatra.servlet.FileUploadSupport

class WordService extends WordServiceStack with FileUploadSupport with FlashMapSupport {

  get("/") {
    val removeNewLinesMessage = "Remove new line breaks in source"
    val newLinesMessage = "Creante new line for each for each sentence"
    val replaceAllButFirstMessage = "Replace all but first instance"

    <html>
      <head>
        <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen"></link>
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        </head>
        <body>
      <h2>Input text here:</h2>
      <form method="post" enctype="multipart/form-data">
        <textarea class="form-control" name="filecontents" rows="25"></textarea>
        <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}<br/>
        <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}<br/>
        <div class="panel-group">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">Replacement Options</a>
              </h4>
            </div>
            <div id="collapse1" class="panel-collapse collapse">
              <div class="panel-body">
                <table>
                  <tr>
                    <th>Replace this term:</th>
                    <th>With this:</th>
                  </tr>
                  <tr>
                    <td><input type="text" name="replace1"></input></td>
                    <td><input type="text" name="replacement1"></input></td>
                    <td><input type="checkbox" name="replaceallbutfirst1"/>{replaceAllButFirstMessage}</td>
                  </tr>
                  <tr>
                    <td><input type="text" name="replace2"></input></td>
                    <td><input type="text" name="replacement2"></input></td>
                    <td><input type="checkbox" name="replaceallbutfirst2"/>{replaceAllButFirstMessage}</td>
                  </tr>
                  <tr>
                    <td><input type="text" name="replace3"></input></td>
                    <td><input type="text" name="replacement3"></input></td>
                    <td><input type="checkbox" name="replaceallbutfirst3"/>{replaceAllButFirstMessage}</td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
        </div>
        <input type="submit" class="btn btn-primary blue"/>
      </form>
      <div class="panel-group">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" href="#collapse1">File Option</a>
            </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
              <form method="post" enctype="multipart/form-data">
              <input type="file" name="thefile" />
              <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}
              <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}
              <table>
                <tr>
                  <th>Replace this term:</th>
                  <th>With this:</th>
                </tr>
                <tr>
                  <td><input type="text" name="replace1"></input></td>
                  <td><input type="text" name="replacement1"></input></td>
                  <td><input type="checkbox" name="replaceallbutfirst1"/>{replaceAllButFirstMessage}</td>
                </tr>
                <tr>
                  <td><input type="text" name="replace2"></input></td>
                  <td><input type="text" name="replacement2"></input></td>
                  <td><input type="checkbox" name="replaceallbutfirst2"/>{replaceAllButFirstMessage}</td>
                </tr>
                <tr>
                  <td><input type="text" name="replace3"></input></td>
                  <td><input type="text" name="replacement3"></input></td>
                  <td><input type="checkbox" name="replaceallbutfirst3"/>{replaceAllButFirstMessage}</td>
                </tr>
              </table>
              <input type="submit" class="btn btn-primary blue"/>
            </form>
              </div>
          </div>
        </div>
      </div>
          </body>
        </html>
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

    val removeNewLinesParam = params.getOrElse("removenewlines", "false")
    val removeNewLines = removeNewLinesParam == "on"

    def extractReplacementInstructions: List[(String, String, Boolean)] = {
      def extractReplacement(num: Int): (String, String, Boolean) = {
        (params.getOrElse(s"replace$num", ""), params.getOrElse(s"replacement$num", ""), params.getOrElse(s"replaceallbutfirst$num", "") == "on")
      }

      val instructions = (for {
        num <- Range(1,4)
      } yield extractReplacement(num)).toList

      instructions.filter(pair => pair match {case (first: String, second: String, _: Boolean) => first != "" && second != ""})
    }

    val otherInstructions = extractReplacementInstructions

    val corrected = WordServiceCorrector.correct(fileContents, createNewLines, removeNewLines, otherInstructions)

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
