package org.dgrald

import java.io.File
import javax.imageio.ImageIO
import net.sourceforge.tess4j._
import org.apache.commons.io.FileUtils

import scala.util.Random

/**
  * Created by dylangrald on 9/22/16.
  */
object ImageFileTextParser {

  def getTextFromFile(file: Array[Byte]): String = {
    val fileName = s"/tmp/${new Random().nextInt(10000)}.jpeg"
    FileUtils.writeByteArrayToFile(new File(fileName), file)
    val writtenFile = ImageIO.read(new File(fileName))
    val instance = new Tesseract1()
    instance.doOCR(writtenFile)
  }
}
