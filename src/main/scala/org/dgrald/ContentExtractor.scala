package org.dgrald

import org.dgrald.file.parsers.FileParser
import org.dgrald.word.correctors.WordServiceCorrector

/**
  * Created by dylangrald on 9/24/16.
  */
abstract class ContentExtractor {
  def getContent(requestParams: RequestParams): (String, String)
}

object ContentExtractor {
  def apply(corrector: WordServiceCorrector, fileParser: FileParser): ContentExtractor = new ContentExtractorImplementation(corrector, fileParser)

  def apply(): ContentExtractor = apply(WordServiceCorrector(), FileParser())
}

private class ContentExtractorImplementation(corrector: WordServiceCorrector, fileParser: FileParser) extends ContentExtractor {
  def getContent(requestParams: RequestParams): (String, String) = {

    val textCorrection: (String, Boolean) = requestParams.getParam("filecontents") match {
      case Some(contents) => (contents, true)
      case None => fileParser.parseFile(requestParams)
    }

    val newLinesParam = requestParams.getParam("linebreaks", "false")
    val createNewLines = newLinesParam == "on"

    val removeNewLinesParam = requestParams.getParam("removenewlines", "false")
    val removeNewLines = removeNewLinesParam == "on"

    val asterisks = requestParams.getParam("asterisks", "false")
    val addAsterisks = asterisks == "on"

    def extractReplacementInstructions: List[(String, String, Boolean)] = {
      def extractReplacement(num: Int): (String, String, Boolean) = {
        (requestParams.getParam(s"replace$num", ""), requestParams.getParam(s"replacement$num", ""), requestParams.getParam(s"replaceallbutfirst$num", "") == "on")
      }

      val instructions = (for {
        num <- Range(1, 4)
      } yield extractReplacement(num)).toList

      instructions.filter(pair => pair match {
        case (first: String, second: String, _: Boolean) => first != "" && second != ""
      })
    }

    val otherInstructions = extractReplacementInstructions

    val runCorrections = textCorrection._2

    val output = if (runCorrections) {
      corrector.correct(textCorrection._1, createNewLines, removeNewLines, addAsterisks, otherInstructions)
    } else {
      ""
    }

    (textCorrection._1, output)
  }
}
