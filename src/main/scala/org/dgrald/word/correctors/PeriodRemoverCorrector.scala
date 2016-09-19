package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/18/16.
  */
object PeriodRemoverCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val periodFollowedByNewLineRegex = "\\.[\\s]*\\n".r
    val intermediateInput = periodFollowedByNewLineRegex.replaceAllIn(input, "\n")

    val periodAtEndOfStringRegex = "\\.[\\s]*\\z".r
    periodAtEndOfStringRegex.replaceAllIn(intermediateInput, "")
  }
}
