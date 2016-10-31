package org.dgrald.word.correctors

/**
  * Created by dylangrald on 10/31/16.
  */
object PValueCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val regex = s"(\\b)[pP](\\s)${Constants.comparisonSymbolRegex}[0-9\\.]+".r

    regex.replaceAllIn(input, regexMatch => {
      regexMatch.toString().toLowerCase.replaceAll(" ", "")
    })
  }
}
