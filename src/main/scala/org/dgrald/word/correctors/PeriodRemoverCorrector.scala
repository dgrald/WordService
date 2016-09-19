package org.dgrald.word.correctors

/**
  * Created by dylangrald on 9/18/16.
  */
object PeriodRemoverCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val regex = ".[\\s]*\\n".r
    val intermediateInput = regex.replaceAllIn(input, "\n")
    val endOfStringRegex = ".[\\s]*\\z".r
    endOfStringRegex.replaceAllIn(intermediateInput, "")
  }
}
