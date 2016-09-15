package org.dgrald.word.correctors

import org.dgrald.word.correctors.Corrector

/**
  * Created by dylangrald on 8/28/16.
  */
object RemoveNewLineAndTabCorrector extends Corrector {

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val newLineFollowedByPeriodRegex = "[.][\\n\\r]+".r
    val newInput = newLineFollowedByPeriodRegex.replaceAllIn(input, ". ")

    val whitespaceRegex = "[\\n\\r\\t]".r
    whitespaceRegex.replaceAllIn(newInput, "")
  }

}
