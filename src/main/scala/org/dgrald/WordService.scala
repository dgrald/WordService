package org.dgrald

import org.scalatra._
import org.scalatra.servlet.FileUploadSupport

class WordService extends WordServiceStack with FileUploadSupport with FlashMapSupport {

  val title = "Text Correction Tool"

  get("/") {
    getMainPage()
  }

  post("/") {
    val contentExtractor = ContentExtractor()
    val requestParams = RequestParams(params, fileParams)
    val output = contentExtractor.getContent(requestParams)
    getMainPage(output._1, output._2)
  }

  def getMainPage(input: String = "", output: String = "") = {
    val runCorrectionsMessage = "Run text correction tool automatically"
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
        <title>{title}</title>
      </head>

      <body>
        <!-- Button trigger modal -->
        <div style="padding: 4px; float: right;">
          <button type="button" class="btn-info btn" data-toggle="modal" data-target="#myModal">Information</button>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Information</h4>
              </div>
              <div class="modal-body">
                {InfoPage.info}
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!-- Actual body !-->
        <div style="padding-left: 6px; padding-right: 15px;">
          <h3>Input text here:</h3>
          <form method="post" enctype="multipart/form-data" style="margin-bottom: 6px; padding-bottom: 0px;">
            <textarea rows="25" wrap="soft" name="filecontents" class="form-control">{input}</textarea>
            <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}<br/>
            <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}<br/>
            <input type="checkbox" name="asterisks"/>{asterisksMessage}<br/>
            <div class="panel-group" style="margin-bottom: 6px;">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h4 class="panel-title">
                    <a data-toggle="collapse" href="#collapse1">{substitutionMessage}</a>
                  </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                  <div class="panel-body">
                    <table class="table">
                      <tr>
                        <th>Replace this term:</th>
                        <th>With this:</th>
                        <th>{replaceAllButFirstMessage}</th>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace1" size="50"></input></td>
                        <td><input type="text" name="replacement1" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst1"/></td>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace2" size="50"></input></td>
                        <td><input type="text" name="replacement2" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst2"/></td>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace3" size="50"></input></td>
                        <td><input type="text" name="replacement3" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst3"/></td>
                      </tr>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            <input type="submit" class="btn btn-primary blue"/>
          </form>
          <h3>Output</h3>
          <div style="padding-bottom: 6px;">
            <textarea rows="25" wrap="soft" autofocus="true" onfocus="this.select()" class="form-control">{output}</textarea>
          </div>
          <!-- image file section !-->
          <div class="panel-group">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" href="#collapse3">Image File Option</a>
                </h4>
              </div>
              <div id="collapse3" class="panel-collapse collapse">
                <div class="panel-body">
                  <form method="post" enctype="multipart/form-data">
                    <input type="file" name="imagefile" />
                    <input type="checkbox" name="runcorrectionsimage" checked="true"/>{runCorrectionsMessage}<br/>
                    <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}
                    <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}
                    <input type="checkbox" name="asterisks"/>{asterisksMessage}<br/>
                    <table class="table">
                      <tr>
                        <th>Replace this term:</th>
                        <th>With this:</th>
                        <th>{replaceAllButFirstMessage}</th>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace1" size="50"></input></td>
                        <td><input type="text" name="replacement1" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst1"/></td>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace2" size="50"></input></td>
                        <td><input type="text" name="replacement2" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst2"/></td>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace3" size="50"></input></td>
                        <td><input type="text" name="replacement3" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst3"/></td>
                      </tr>
                    </table>
                    <input type="submit" class="btn btn-primary blue"/>
                  </form>
                </div>
              </div>
            </div>
          </div>

          <!-- PDF file section !-->
          <div class="panel-group">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h4 class="panel-title">
                  <a data-toggle="collapse" href="#collapse2">PDF File Option</a>
                </h4>
              </div>
              <div id="collapse2" class="panel-collapse collapse">
                <div class="panel-body">
                  <form method="post" enctype="multipart/form-data">
                    <input type="file" name="pdffile" />
                    <input type="checkbox" name="runcorrectionspdf" checked="true"/>{runCorrectionsMessage}<br/>
                    <input type="checkbox" name="linebreaks" checked="true"/>{newLinesMessage}
                    <input type="checkbox" name="removenewlines" checked="true"/>{removeNewLinesMessage}
                    <input type="checkbox" name="asterisks"/>{asterisksMessage}<br/>
                    <table class="table">
                      <tr>
                        <th>Replace this term:</th>
                        <th>With this:</th>
                        <th>{replaceAllButFirstMessage}</th>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace1" size="50"></input></td>
                        <td><input type="text" name="replacement1" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst1"/></td>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace2" size="50"></input></td>
                        <td><input type="text" name="replacement2" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst2"/></td>
                      </tr>
                      <tr>
                        <td><input type="text" name="replace3" size="50"></input></td>
                        <td><input type="text" name="replacement3" size="50"></input></td>
                        <td><input type="checkbox" name="replaceallbutfirst3"/></td>
                      </tr>
                    </table>
                    <input type="submit" class="btn btn-primary blue"/>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </body>
    </html>
  }
}
