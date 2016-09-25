package org.dgrald.file.parsers

import org.dgrald.RequestParams

/**
  * Created by dylangrald on 9/25/16.
  */
abstract class FileParser {
  def parseFile(params: RequestParams): (String, Boolean)
}

object FileParser {
  def apply(): FileParser = new FileParserImplementation()
}

private class FileParserImplementation extends FileParser {
  override def parseFile(requestParams: RequestParams): (String, Boolean) = {
    def getRunCorrections(paramName: String): Boolean = {
      val runCorrections = requestParams.getParam(paramName, "false")
      runCorrections == "on"
    }

    requestParams.getFileParam("pdffile") match {
      case Some(file) => PdfTextParser.getTextFromPdf(file) match {
        case Some(parsed) => (parsed, getRunCorrections("runcorrectionspdf"))
        case _ => throw new Exception("Exception occured while attempting to parse PDF file")
      }
      case None =>
        requestParams.getFileParam("imagefile") match {
          case Some(file) => (ImageTextParser.parseText(file), getRunCorrections("runcorrectionsimage"))
          case _ => throw new Exception("Could not find content to parse")
        }
    }
  }
}