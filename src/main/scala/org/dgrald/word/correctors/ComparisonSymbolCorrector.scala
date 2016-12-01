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

      def previousWordIsSingleLetter(): Boolean = {
        if(comparisonWithSpacesMatch.start == 1) {
          input.charAt(comparisonWithSpacesMatch.start - 1).isLetter
        } else {
          input.charAt(comparisonWithSpacesMatch.start - 1).isLetter && input.charAt(comparisonWithSpacesMatch.start - 2).isWhitespace
        }
      }

      val spacesStripped = comparisonWithSpacesMatch.toString.replaceAll("\\s", "")
      if(previousCharIsWhiteSpace() || previousWordIsSingleLetter()) {
        spacesStripped
      } else {
        s" $spacesStripped"
      }
    })
  }
}
