package org.dgrald

import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

/**
  * Created by dylangrald on 8/29/16.
  */
object PdfTextParser {

  def getTextFromPdf(file: Array[Byte]): Option[String] = {
    try {
      val pdf = PDDocument.load(file)
      val stripper = new PDFTextStripper
      Some(stripper.getText(pdf))
    } catch {
      case t: Throwable =>
        t.printStackTrace
        None
    }
  }

}
