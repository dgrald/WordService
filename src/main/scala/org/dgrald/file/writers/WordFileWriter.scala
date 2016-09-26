package org.dgrald.file.writers

import java.io.{File, FileOutputStream}
import java.util.UUID

import org.apache.poi.xwpf.usermodel.XWPFDocument

/**
  * Created by dylangrald on 9/26/16.
  */
object WordFileWriter {

  def writeFile(input: String): File = {

    val doc = new XWPFDocument()
    val paragraph = doc.createParagraph()
    val run = paragraph.createRun()
    run.setText(input)

    val filePath = s"target/${UUID.randomUUID()}.docx"
    val outputStream = new FileOutputStream(filePath)
    doc.write(outputStream)

    outputStream.close()

    val file = new File(filePath)
    file
  }
}
