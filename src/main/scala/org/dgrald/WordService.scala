package org.dgrald

import org.dgrald.word.correctors.WordServiceCorrector
import org.scalatra._
import org.scalatra.servlet.FileUploadSupport

class WordService extends WordServiceStack with FileUploadSupport with FlashMapSupport {

  get("/") {
    val removeNewLinesMessage = "Remove line breaks in source"
    val newLinesMessage = "Put each sentence on its own line"
    val replaceAllButFirstMessage = "Replace all but first instance"
    val asterisksMessage = "Add asterisk at the beginning of each line"

    val substitutionMessage = "Custom Terms Substitution"

    <html>
      <head>
        <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen"></link>
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <title>Text Correction Input</title>
        </head>

      <body>
      <h2>Input text here:</h2>
      <form method="post" enctype="multipart/form-data">
        <textarea class="form-control" name="filecontents" rows="25"></textarea>
        <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}<br/>
        <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}<br/>
        <input type="checkbox" name="asterisks"/>{asterisksMessage}<br/>
        <div class="panel-group">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">{substitutionMessage}</a>
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
              <a data-toggle="collapse" href="#collapse2">File Option</a>
            </h4>
          </div>
          <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
              <form method="post" enctype="multipart/form-data">
              <input type="file" name="thefile" />
              <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}
              <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}
                <input type="checkbox" name="asterisks"/>{asterisksMessage}<br/>
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

    val asterisks = params.getOrElse("asterisks", "false")
    val addAsterisks = asterisks == "on"

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

    val corrected = WordServiceCorrector.correct(fileContents, createNewLines, removeNewLines, addAsterisks, otherInstructions)

    <html>
      <head>
        <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen"></link>
        <script type="text/javascript" src="js/jquery-3.1.0.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <title>Text Correction Result</title>
      </head>
      <body>
    <form method="get" enctype="multipart/form-data">
      <div>
        <input type="submit" value="Back" class="btn btn-primary blue"/>
      </div>
      <div>
        <textarea rows="25" wrap="soft" autofocus="true" onfocus="this.select()" class="form-control">{corrected}</textarea>
      </div>
    </form>
        </body>
      </html>
  }

}
