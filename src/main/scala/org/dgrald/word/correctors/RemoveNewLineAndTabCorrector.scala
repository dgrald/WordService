package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
object RemoveNewLineAndTabCorrector extends Corrector {

  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val tabsAndNewLinesRegex = "[\\n\\r\\t]".r
    tabsAndNewLinesRegex.replaceAllIn(input, " ")
  }

}
