package org.dgrald

/**
  * Created by dylangrald on 8/28/16.
  */
object ComparisonSymbolCorrector extends Corrector {
  override def correct(input: String): String = {
    val lessThanOrEqualToUnicode = "\u2264"
    val greaterThanOrEqualToUnicode = "\u2265"
    val comparisonSymbolWithWhitespaceAndNumbersRegex = s"[><{$lessThanOrEqualToUnicode}{$greaterThanOrEqualToUnicode}][=]{0,1}[\\s]+[0-9.]+".r

    comparisonSymbolWithWhitespaceAndNumbersRegex.replaceAllIn(input, (comparisonWithSpacesMatch) => {
      comparisonWithSpacesMatch.toString.replaceAll("\\s", "")
    })
  }
}
