package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/18/16.
  */
object PeriodRemoverCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val periodFollowedByNewLineRegex = "\\.[\\s]*\\n".r
    val intermediateInput = periodFollowedByNewLineRegex.replaceAllIn(input, regexMatch => {
      if(regexMatch.start > 0) {
        val substring = input.substring(0, regexMatch.start + 1)
        if(Constants.exceptionsToTrailingPeriod.contains(substring.split(" ").last)){
          regexMatch.toString()
        } else {
          "\n"
        }
      } else {
        "\n"
      }
    })

    if(Constants.exceptionsToTrailingPeriod.contains(intermediateInput.trim.split(" ").last)) {
      return intermediateInput
    }

    val periodAtEndOfStringRegex = "\\.[\\s]*\\z".r
    periodAtEndOfStringRegex.replaceAllIn(intermediateInput, "")
  }
}
