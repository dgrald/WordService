package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object ComparisonSymbolCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val comparisonSymbolWithWhitespaceAndNumbersRegex = s"${Constants.comparisonSymbolRegex}[\\s]*[0-9.]+".r

    comparisonSymbolWithWhitespaceAndNumbersRegex.replaceAllIn(input, (comparisonWithSpacesMatch) => {
      def previousCharIsWhiteSpace(): Boolean = {
        comparisonWithSpacesMatch.start == 0 || input.charAt(comparisonWithSpacesMatch.start - 1).isWhitespace
      }

      def isPrecededByVariable(): Boolean = {
        val previousChar = input.charAt(comparisonWithSpacesMatch.start - 1)
        if(comparisonWithSpacesMatch.start == 1) {
          previousChar.isLetter
        } else {
          val twoCharsBehind = input.charAt(comparisonWithSpacesMatch.start - 2)
          previousChar.isLetter && (twoCharsBehind.isWhitespace || twoCharsBehind == '(')
        }
      }

      val spacesStripped = comparisonWithSpacesMatch.toString.replaceAll("\\s", "")
      if(previousCharIsWhiteSpace() || isPrecededByVariable()) {
        spacesStripped
      } else {
        s" $spacesStripped"
      }
    })
  }
}
